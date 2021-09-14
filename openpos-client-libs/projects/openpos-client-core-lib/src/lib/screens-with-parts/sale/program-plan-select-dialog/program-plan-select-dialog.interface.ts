import {IAbstractScreen} from "../../../core/interfaces/abstract-screen.interface";
import {Plan} from "../program-interface";

export interface ProgramPlanSelectDialogInterface extends IAbstractScreen {
    programCopy: String;
    subscriptionPlans: Plan[];
}