import { Challenge } from './challenge.model';
import { SolutionType } from './enums/solution-type.enum';

export class Solution {
    id: number;
    content: string;
    challenge: Challenge;
    solutionType: SolutionType;
}