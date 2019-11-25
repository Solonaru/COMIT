import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Event } from 'src/app/models/event.model';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-event-manager',
  templateUrl: './event-manager.component.html',
  styleUrls: ['./event-manager.component.css']
})
export class EventManagerComponent implements OnInit, OnDestroy {
  modal: Boolean = false;
  selectedAction: String = '';

  events: Event[] = [];
  subscriptions: Subscription[] = [];

  constructor(private eventService: EventService) { }

  ngOnInit() {
    this.subscriptions.push(this.eventService.eventsChanged
      .subscribe(
        (events: Event[]) => {
          this.events = events;
        }
      ));

    this.eventService.getEvents();
  }

  toggleModal(e, action) {
    e.preventDefault();
    this.modal = !this.modal;

    if (action) this.selectedAction = action;
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => {
      subscription.unsubscribe();
    })
  }

}
