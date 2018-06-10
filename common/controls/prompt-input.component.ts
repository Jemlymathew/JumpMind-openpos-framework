import { FormControl, Validators, FormGroup, FormGroupDirective, NgForm } from '@angular/forms';
import { Component, Input, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';
import { DatePipe } from '@angular/common';
import createAutoCorrectedDatePipe from 'text-mask-addons/dist/createAutoCorrectedDatePipe';
import { TextMask, IMaskSpec, ITextMask } from '../textmask';
import { ErrorStateMatcher } from '@angular/material';

@Component({
    selector: 'app-prompt-input',
    templateUrl: './prompt-input.component.html'
})

export class PromptInputComponent implements OnInit{

    @Input() placeholderText: string;
    @Input() responseType: string;
    @Input() responseText: string;
    @Input() promptIcon: string;
    @Input() hintText: string;
    @Input() maskSpec: IMaskSpec;
    @Input() minLength: number;
    @Input() maxLength: number;
    @Input() promptFormGroup: FormGroup;
    @Input() readOnly: boolean = false;

    inputType: string;

    formatter: string;
    _textMask: ITextMask; // Mask object built for text-mask

    constructor(private datePipe: DatePipe) {
    }

    isNumericField(): boolean {
        if (this.responseType) {
            return ['numerictext', 'money', 'phone', 'postalCode', 'percent', 'percentint', 'income', 'decimal']
              .indexOf(this.responseType.toLowerCase()) >= 0;
        } else {
            return false;
        }
    }

    ngOnInit(): void {
        this.formatter = this.responseType;

        if (this.maskSpec) {
            const newMask = TextMask.instance(this.maskSpec);
            this._textMask = newMask;
        } else {
            this._textMask = TextMask.NO_MASK;
        }        
    }

}
