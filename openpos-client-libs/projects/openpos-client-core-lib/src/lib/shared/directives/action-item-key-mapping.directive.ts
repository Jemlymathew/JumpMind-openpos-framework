import { Directive, Input, Renderer2, ElementRef, OnDestroy, EventEmitter, Output } from '@angular/core';
import { KeyPressProvider } from '../providers/keypress.provider';
import { Subscription } from 'rxjs';
import { CONFIGURATION } from '../../configuration/configuration';
import { IActionItem } from '../../core/actions/action-item.interface';
import { ActionService } from '../../core/actions/action.service';

@Directive({
    // tslint:disable-next-line:directive-selector
    selector: '[actionItem]'
})
export class ActionItemKeyMappingDirective implements OnDestroy {
    @Output() actionClick = new EventEmitter();

    @Input()
    set actionItem(item: IActionItem) {
        if ( this.keyDownSubscription ) {
            this.keyDownSubscription.unsubscribe();
        }
        if ( this.keyUpSubscription ) {
            this.keyUpSubscription.unsubscribe();
        }

        this.keyDownSubscription = this.keyPresses.subscribe( item.keybind, 100, event => this.handleKeypress(event, item));
        this.keyUpSubscription = this.keyPresses.subscribe(item.keybind, 101, event => this.handleKeypress(event, item), null, 'keyup');
    }

    private keyDownSubscription: Subscription;
    private keyUpSubscription: Subscription;

    constructor(
        private renderer: Renderer2,
        private el: ElementRef,
        private keyPresses: KeyPressProvider,
        private actionService: ActionService ) {

    }

    handleKeypress(event: KeyboardEvent, item: IActionItem) {
        // ignore repeats
        if ( event.repeat || !CONFIGURATION.enableKeybinds ) {
            return;
        }
        if ( event.type === 'keydown') {
            this.renderer.addClass(this.el.nativeElement, 'key-mapping-active');
            if (this.actionClick.observers !== null && this.actionClick.observers.length > 0) {
                this.actionClick.emit();
            } else {
                this.actionService.doAction(item);
            }
            event.preventDefault();
        } else if ( event.type === 'keyup') {
            this.renderer.removeClass(this.el.nativeElement, 'key-mapping-active');
        }
}

    ngOnDestroy(): void {
        if ( this.keyDownSubscription ) {
            this.keyDownSubscription.unsubscribe();
        }
        if ( this.keyUpSubscription ) {
            this.keyUpSubscription.unsubscribe();
        }
    }

}
