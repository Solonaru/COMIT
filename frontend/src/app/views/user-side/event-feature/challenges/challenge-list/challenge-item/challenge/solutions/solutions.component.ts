import { Component, OnInit } from '@angular/core';
import { Params, ActivatedRoute } from '@angular/router';
import { Challenge } from 'src/app/models/challenge.model';
import { ChallengeService } from 'src/app/services/challenge.service';

@Component({
  selector: 'app-solutions',
  templateUrl: './solutions.component.html',
  styleUrls: ['./solutions.component.css']
})
export class SolutionsComponent implements OnInit {
  challenge: Challenge;
  modal: Boolean = false;
  show: Boolean = false;

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

  toggleModal() {
    this.modal = !this.modal;
  }

  showSolutions() {
    this.show = !this.show;
    this.toggleModal();
  }
}
