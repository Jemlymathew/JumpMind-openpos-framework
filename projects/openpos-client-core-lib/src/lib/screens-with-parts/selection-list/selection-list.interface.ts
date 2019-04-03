import { IAbstractScreen } from '../../core/interfaces/abstract-screen.interface';
import { SelectionListItem } from './selection-list-item.interface';
import { IActionItem } from '../../core/interfaces/menu-item.interface';

export interface SelectionListInterface extends IAbstractScreen {
    selectionList: SelectionListItem[];
    selectionButtons: IActionItem[];
    nonSelectionButtons: IActionItem[];
    multiSelect: boolean;
    defaultSelect: boolean;
    defaultSelectItemIndex: number;
    selectionChangedAction: string;
    instructions: string;
    numberItemsPerPage: number;
    sausageLinks: IActionItem[];
}
