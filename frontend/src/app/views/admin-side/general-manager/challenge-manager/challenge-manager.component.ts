import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ChallengeService } from 'src/app/services/challenge.service';
import { Challenge } from 'src/app/models/challenge.model';
import { EventService } from 'src/app/services/event.service';
import { Event } from 'src/app/models/event.model';

@Component({
  selector: 'app-challenge-manager',
  templateUrl: './challenge-manager.component.html',
  styleUrls: ['./challenge-manager.component.css', './../general-manager.component.css']
})
export class ChallengeManagerComponent implements OnInit, OnDestroy {
  modal: Boolean = false;
  selectedAction: String = 'add';

  challenge: Challenge = null;
  challenges: Challenge[] = [];

  events: { id: number, name: string }[] = [
    { id: -1, name: 'All' }
  ];

  subscriptions: Subscription[] = [];

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

  toggleModal(e, action?: string, challenge?: Challenge) {
    e.preventDefault();
    this.modal = !this.modal;

    if (action) {
      this.selectedAction = action;

      if ('add' === action) {
        this.challenge = new Challenge();
        this.challenge.event = new Event();
      }

      if (challenge) {
        this.challenge = challenge;
      } else {
        this.process_challenge(action);
      }
    } else {
      this.challenge = null;
    }

  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => {
      subscription.unsubscribe();
    })
  }

  private process_challenge(action: string): void {

    switch (action) {
      case 'add_confirm': {
        this.add_challenge();
        break;
      }
      case 'update_confirm': {
        this.update_challenge();
        break;
      }
      case 'delete_confirm': {
        this.delete_challenge();
        break;
      }
    }

  }

  private add_challenge(): void {
    this.challengeService.addChallenge(this.challenge).subscribe();
  }

  private update_challenge(): void {
    this.challengeService.updateChallenge(this.challenge).subscribe();
  }

  private delete_challenge(): void {
    this.challengeService.deleteChallenge(this.challenge).subscribe();
  }

}
