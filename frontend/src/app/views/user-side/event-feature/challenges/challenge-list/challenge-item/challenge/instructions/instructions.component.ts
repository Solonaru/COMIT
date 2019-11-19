import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Challenge } from 'src/app/models/challenge.model';
import { ChallengeService } from 'src/app/services/challenge.service';

@Component({
  selector: 'app-instructions',
  templateUrl: './instructions.component.html',
  styleUrls: ['./instructions.component.css']
})
export class InstructionsComponent implements OnInit {
  challenge: Challenge;

  constructor(
    private route: ActivatedRoute,
    private challengeService: ChallengeService) { }

  ngOnInit() {
    this.route.parent.params
      .subscribe(
        (params: Params) => {
          this.challengeService.getChallengeById(+params['id']).subscribe(data => {
            this.challenge = data;
          });
        }
      );
  }

}
