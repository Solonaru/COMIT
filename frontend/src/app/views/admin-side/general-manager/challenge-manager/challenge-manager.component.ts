import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ChallengeService } from 'src/app/services/challenge.service';
import { Challenge } from 'src/app/models/challenge.model';
import { EventService } from 'src/app/services/event.service';
import { Event } from 'src/app/models/event.model';

@Component({
  selector: 'app-challenge-manager',
  templateUrl: './challenge-manager.component.html',
  styleUrls: ['./challenge-manager.component.css']
})
export class ChallengeManagerComponent implements OnInit, OnDestroy {

  challenges: Challenge[] = [];
  subscriptions: Subscription[] = [];
  events: { id: number, name: string }[] = [
    { id: -1, name: 'All' }
  ];

  constructor(
    private eventService: EventService,
    private challengeService: ChallengeService) { }

  ngOnInit() {
    this.subscriptions.push(this.eventService.eventsChanged
      .subscribe(
        (events: Event[]) => {
          events.forEach(event => {
            this.events.push({ id: event.id, name: event.name });
          });
        }
      ));

    this.subscriptions.push(this.challengeService.challengesChanged
      .subscribe(
        (challenges: Challenge[]) => {
          this.challenges = challenges;
        }
      ));

    this.eventService.getEvents();
    this.challengeService.getChallenges();
  }

  onSelect(event: { id: number, name: string }) {
    if (event.id != -1) {
      this.challengeService.getChallengesByEventId(event.id);
    } else {
      this.challengeService.getChallenges();
    }
  }

  limitLength(string: string): string {
    if (string.length > 80)
      return string.substring(0, 80) + ' ...';
    return string;
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => {
      subscription.unsubscribe();
    })
  }

}
