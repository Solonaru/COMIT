import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

import { Challenge } from 'src/app/models/challenge.model';
import { ChallengeService } from 'src/app/services/challenge.service';

@Component({
  selector: 'app-challenge',
  templateUrl: './challenge.component.html',
  styleUrls: ['./challenge.component.css']
})
export class ChallengeComponent implements OnInit {
  challenge: Challenge;
  modal: Boolean = false;
  show: Boolean = false;
  
  constructor(private route: ActivatedRoute, private challengeService: ChallengeService) { }

  ngOnInit() {
    this.route.params
    .subscribe(
      (params: Params) => {
        this.challengeService.getChallengeById(+params['id']).subscribe(data => {
          this.challenge = data;
        });
      }
    );
  }

  toggleModal() {
    this.modal = !this.modal;
  }

  showTips() {
    this.show = !this.show;
    this.toggleModal();
  }
}
