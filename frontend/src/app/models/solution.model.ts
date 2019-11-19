import { Challenge } from './challenge.model';
import { SolutionType } from './enums/solution-type.enum';

export interface Solution {
    id: number;
    content: string;
    challenge: Challenge;
    solutionType: SolutionType;
}