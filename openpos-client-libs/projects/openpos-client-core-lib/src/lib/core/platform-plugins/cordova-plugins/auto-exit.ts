import { IPlatformPlugin } from '../platform-plugin.interface';
import { Observable, Subject } from 'rxjs';
import { filter } from 'rxjs/operators';
import { SessionService } from '../../services/session.service';
import { Injectable } from '@angular/core';
import { ActionMessage } from '../../messages/action-message';
import { MessageTypes } from '../../messages/message-types';

@Injectable({
    providedIn: 'root'
})
export class ExitApp implements IPlatformPlugin {

    private ExitAppPlugin;

    constructor(private sessionService: SessionService) { }

    name(): string {
        return 'ExitApp';
    }

    pluginPresent(): boolean {
        return !!window.hasOwnProperty('ExitApp');
    }

    initialize(): Observable<string> {
        return Observable.create((initialized: Subject<string>) => {
            // tslint:disable-next-line:no-string-literal
            this.ExitAppPlugin = window['ExitApp'];
            if (!this.ExitAppPlugin) {
                initialized.error(`Tried to initialize plugin ${this.name()} which is not present`);
            }

            this.sessionService.getMessages('Proxy').pipe(
                filter(m => m.proxyType === 'AppManagement')
            ).subscribe(message => {
                this.forwardMessage(message);
            });

            initialized.next(`Successfully intitialized ${this.name()}`);
            initialized.complete();
        });
    }

    forwardMessage(message: any) {
        if (message.action === 'Exit') {
            this.ExitAppPlugin.processMessage(
                response => { this.handleSuccess(message, response); },
                response => { this.handleError(message, response); }
            );
        }
    }

    handleSuccess(message: any, response: string) {
        const responseMessage = new ActionMessage('response', true,{ messageId: message.messageId, payload: response, success: true });
        responseMessage.type = MessageTypes.PROXY;
        this.sessionService.sendMessage(responseMessage);
    }

    handleError(message: any, response: string) {
        const responseMessage = new ActionMessage('response', true, { messageId: message.messageId, payload: response, success: false });
        responseMessage.type = MessageTypes.PROXY;
        this.sessionService.sendMessage(responseMessage);
    }

}
