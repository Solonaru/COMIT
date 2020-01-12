import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Event } from 'src/app/models/event.model';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-event-manager',
  templateUrl: './event-manager.component.html',
  styleUrls: ['./event-manager.component.css', './../general-manager.component.css']
})
export class EventManagerComponent implements OnInit, OnDestroy {
  modal: Boolean = false;
  selectedAction: String = 'add';

  event: Event = null;
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

  toggleModal(e, action?: string, event?: Event) {
    e.preventDefault();
    this.modal = !this.modal;

    if (action) {
      this.selectedAction = action;

      if ('add' === action) {
        this.event = new Event();
      }

      if (event) {
        this.event = event;
      } else {
        this.process_event(action);
      }
    } else {
      this.event = null;
    }

  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => {
      subscription.unsubscribe();
    })
  }

  private process_event(action: string): void {

    switch (action) {
      case 'add_confirm': {
        this.add_event();
        break;
      }
      case 'update_confirm': {
        this.update_event();
        break;
      }
      case 'delete_confirm': {
        this.delete_event();
        break;
      }
    }

  }

  private add_event(): void {
    this.eventService.addEvent(this.event).subscribe(resp => {
      console.log("Event successfully added");
    });
  }

  private update_event(): void {
    this.eventService.updateEvent(this.event).subscribe(resp => {
      console.log("Event successfully update");
    });
  }

  private delete_event(): void {
    this.eventService.deleteEvent(this.event).subscribe(resp => {
      console.log("Event successfully deleted");
    });
  }

}
