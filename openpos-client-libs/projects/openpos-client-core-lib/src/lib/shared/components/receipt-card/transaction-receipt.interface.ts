import { ITransactionReceiptLine } from './transaction-receipt-line.interface';
import { ITotal } from '../../../core/interfaces/total.interface';

export interface ITransactionReceipt {
    barcode: string;
    transactionNumber: number;
    webOrderId: string;
    webOrderLabel: string;
    webOrderStyleClasses: string;
    transactionInfoSection: ITransactionReceiptLine[];
    totalsInfoSection: ITransactionReceiptLine[];
    transactionTotal: ITotal;
    icon: string;
    webOrderIcon: string;
}
