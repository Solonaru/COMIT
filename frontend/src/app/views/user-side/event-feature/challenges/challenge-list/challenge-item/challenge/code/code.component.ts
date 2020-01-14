import { Component, OnInit } from '@angular/core';
import { SolutionService } from 'src/app/services/solution.service';
import { Solution } from 'src/app/models/solution.model';
import { ChallengeService } from 'src/app/services/challenge.service';

@Component({
  selector: 'app-code',
  templateUrl: './code.component.html',
  styleUrls: ['./code.component.css']
})
export class CodeComponent implements OnInit {

  content: string = "";

  constructor(
    private challengeService: ChallengeService,
    private solutionService: SolutionService) { }

  ngOnInit() {
  }

  onSubmit() {
    let solution = new Solution();
    solution.content = this.content;
    solution.challenge = this.challengeService.getChallenge();
    this.solutionService.addSolution(solution).subscribe();
    this.content = "";
  }

}
