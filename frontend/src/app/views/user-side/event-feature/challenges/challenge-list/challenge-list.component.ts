import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Subscription } from 'rxjs';
import { ChallengeService } from 'src/app/services/challenge.service';
import { EnumObjectService } from 'src/app/services/enum-object.service';
import { Challenge } from 'src/app/models/challenge.model';

@Component({
  selector: 'app-challenge-list',
  templateUrl: './challenge-list.component.html',
  styleUrls: ['./challenge-list.component.css']
})
export class ChallengeListComponent implements OnInit, OnDestroy {
  @Input() eventId: number; 
  challenges: Challenge[] = [];
  subscriptions: Subscription[] = [];

  constructor(
    private challengeService: ChallengeService,
    private enumObjectService: EnumObjectService) { }

  ngOnInit() {
    this.subscriptions.push(this.challengeService.challengesChanged
      .subscribe(
        (challenges: Challenge[]) => {
          this.challenges = challenges;
        }
      ));

    this.subscriptions.push(this.enumObjectService.eventTypeChanged
      .subscribe(
        (eventType: string) => {
          this.challengeService.filterChallenges();
        }
      ));

    this.subscriptions.push(this.enumObjectService.technologyChanged
      .subscribe(
        (technology: string) => {
          this.challengeService.filterChallenges();
        }
      ));

    this.subscriptions.push(this.enumObjectService.skillLevelChanged
      .subscribe(
        (skillLevel: string) => {
          this.challengeService.filterChallenges();
        }
      ));

    this.challengeService.getChallengesByEventId(this.eventId);
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => {
      subscription.unsubscribe();
    })
  }

}
