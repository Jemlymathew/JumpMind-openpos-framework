import { IconService } from './../services/icon.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ScreenDirective } from '../common/screen.directive';
import { IScreen } from '../common/iscreen';
import { ScreenService } from './../services/screen.service';
import { DialogComponent } from '../screens/dialog.component';
import { IMenuItem } from '../common/imenuitem';
import { Component, OnInit, OnDestroy, DoCheck, sequence, AfterViewInit, NgZone } from '@angular/core';
import { Type, ViewChild, ComponentFactory } from '@angular/core';
import { SessionService } from '../services/session.service';
import { StatusBarComponent } from '../screens/statusbar.component';
import { FocusDirective } from '../common/focus.directive';
import { Observable } from 'rxjs/Observable';
import { MatDialog, MatDialogRef, MatIconRegistry, MatSnackBar, MatSnackBarRef, SimpleSnackBar } from '@angular/material';
import { OverlayContainer } from '@angular/cdk/overlay';
import { TemplateDirective } from './template.directive';
import { AbstractTemplate } from './abstract-template';
import { Router } from '@angular/router';

export abstract class AbstractApp implements OnDestroy, DoCheck, OnInit {

    private dialogRef: MatDialogRef<IScreen>;

    private previousScreenType: string;

    private previousDialogType: string;

    private dialogOpening: boolean;

    private previousScreenSequenceNumber: number;

    private previousScreenName: string;

    private snackBarRef: MatSnackBarRef<SimpleSnackBar>;

    private personalized: boolean;

    private registered: boolean;

    @ViewChild(TemplateDirective) host: TemplateDirective;

    constructor(public screenService: ScreenService,
        public session: SessionService,
        public dialog: MatDialog,
        public iconService: IconService,
        public snackBar: MatSnackBar,
        public overlayContainer: OverlayContainer,
        protected router: Router,
        public zone: NgZone) {

        console.log('creating ' + this.constructor.name);

    }

    public abstract appName(): string;

    ngOnInit(): void {

        this.session.observableScreen.subscribe(
            screen => this.zone.run(() => this.updateTemplateAndScreens())
        );

        if (!this.registerWithServer()) {
            this.updateTemplateAndScreens();
        }
    }


    ngDoCheck(): void {
        // this.updateTemplateAndScreens();
    }

    public registerWithServer(): boolean {
        if (!this.registered && this.isPersonalized()) {
            console.log('initializing the application');
            this.session.unsubscribe();
            this.session.subscribe(this.appName());
            this.registered = true;
        }
        return this.registered;
    }

    getTheme(): string {
        if (this.session.screen && this.session.screen.theme) {
            localStorage.setItem('theme', this.session.screen.theme);
            return this.session.screen.theme;
        } else if (localStorage.getItem('theme')) {
            return localStorage.getItem('theme');
        } else {
            return 'openpos-theme';
        }
    }

    ngOnDestroy(): void {
        this.session.unsubscribe();
    }

    isPersonalized(): boolean {
        if (!this.personalized && this.session.isPersonalized()) {
            this.personalized = true;
            console.log('already personalized.  setting needs personalization to false');
        }
        return this.personalized;
    }

    updateTemplateAndScreens(): void {
        this.registerWithServer();
        if (this.session.dialog) {
            const dialogType = this.screenService.hasScreen(this.session.dialog.subType) ? this.session.dialog.subType : 'Dialog';
            if (!this.dialogOpening && (!this.dialogRef || this.previousDialogType !== dialogType)) {
                if (this.dialogRef) {
                    console.log('closing dialog');
                    this.dialogRef.close();
                    this.dialogRef = null;
                }
                console.log('opening dialog \'' + dialogType + '\'');
                this.dialogOpening = true;
                setTimeout(() => this.openDialog(), 0);
            }
        } else if (!this.session.dialog && this.dialogRef) {
            console.log('closing dialog');
            this.dialogRef.close();
            this.dialogRef = null;
        }

        if (!this.isPersonalized() && !this.session.screen) {
            console.log('setting up the personalization screen');
            this.session.screen = this.session.getPersonalizationScreen();
        }

        let screen: IScreen = null;
        let template: AbstractTemplate = null;
        if (
            (this.session.screen &&
                ((this.session.screen.sequenceNumber !== this.previousScreenSequenceNumber && this.session.screen.refreshAlways)
                    || this.session.screen.type !== this.previousScreenType
                    || this.session.screen.name !== this.previousScreenName
                )
            )) {

            let templateName: string = null;
            let screenType: string = null;
            let sequenceNumber = -1;
            let screenName: string = null;
            if (this.session.screen && this.session.screen.type) {
                console.log(`Switching screens from ${this.previousScreenType} to ${this.session.screen.type}`);
                templateName = this.session.screen.template;
                screenType = this.session.screen.type;
                sequenceNumber = this.session.screen.sequenceNumber;
                screenName = this.session.screen.name;
            }
            const templateComponentFactory: ComponentFactory<IScreen> = this.screenService.resolveScreen(templateName);
            const viewContainerRef = this.host.viewContainerRef;
            viewContainerRef.clear();
            template = viewContainerRef.createComponent(templateComponentFactory).instance as AbstractTemplate;
            this.previousScreenType = screenType;
            this.previousScreenName = screenName;
            this.overlayContainer.getContainerElement().classList.add(this.getTheme());
            screen = template.installScreen(this.screenService.resolveScreen(screenType), this.session, this);
            template.show(this.session, this);
            screen.show(this.session, this);
            this.previousScreenSequenceNumber = sequenceNumber;
        }

    }

    openDialog() {
        const dialogComponentFactory: ComponentFactory<IScreen> = this.screenService.resolveScreen(this.session.dialog.subType);
        let dialogComponent = DialogComponent;
        this.previousDialogType = 'Dialog';
        // if we resolved a specific screen type use that otherwise just use the default DialogComponent
        if (dialogComponentFactory) {
            dialogComponent = dialogComponentFactory.componentType;
            this.previousDialogType = this.session.dialog.subType;
        }

        this.dialogRef = this.dialog.open(dialogComponent, { disableClose: true });
        this.dialogOpening = false;
        console.log('Dialog \'' + this.previousDialogType + '\' opened');
        this.dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.session.onAction(result);
                this.dialogRef = null;
            }
        });

    }
}
