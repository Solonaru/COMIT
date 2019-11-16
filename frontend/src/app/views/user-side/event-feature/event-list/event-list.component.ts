import { Component, OnInit, OnDestroy } from '@angular/core';
import { EventService } from 'src/app/services/event.service';
import { Subscription } from 'rxjs';
import { EnumObjectService } from 'src/app/services/enum-object.service';
import { Event } from 'src/app/models/event.model';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit, OnDestroy {

  events: Event[] = [];
  subscriptions: Subscription[] = [];

  constructor(
    private eventService: EventService,
    private enumObjectService: EnumObjectService) { }

  ngOnInit() {
    this.subscriptions.push(this.eventService.eventsChanged
      .subscribe(
        (events: Event[]) => {
          this.events = events;
        }
      ));

    this.subscriptions.push(this.enumObjectService.eventTypeChanged
      .subscribe(
        (eventType: string) => {
          this.eventService.filterEvents();
        }
      ));

    this.subscriptions.push(this.enumObjectService.technologyChanged
      .subscribe(
        (technology: string) => {
          this.eventService.filterEvents();
        }
      ));

    this.subscriptions.push(this.enumObjectService.eventStatusChanged
      .subscribe(
        (eventStatus: string) => {
          this.eventService.filterEvents();
        }
      ));

    this.eventService.getEvents();
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => {
      subscription.unsubscribe();
    })
  }

}
