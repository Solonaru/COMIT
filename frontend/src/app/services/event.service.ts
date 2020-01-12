import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { take, tap, map } from 'rxjs/operators';
import { EnumObjectService } from './enum-object.service';
import { Event } from '../models/event.model';
import { DatePipe } from '@angular/common';

@Injectable({ providedIn: 'root' })
export class EventService {
    eventsChanged = new Subject<Event[]>();

    private eventUrl = 'http://localhost:8080/event';
    private httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    private events: Event[] = [];

    constructor(
        private http: HttpClient,
        private enumObjectService: EnumObjectService,
        private datePipe: DatePipe) { }

    getEventById(id: number): Observable<Event> {
        return this.fetchEventById(id);
    }

    getEvents(): void {
        this.fetchEvents().subscribe();
    }

    addEvent(event: Event) {
        return this.http.post<Event>(this.eventUrl + '/add', JSON.stringify(event), this.httpOptions)
            .pipe(map((resp: any) => {
                this.fetchEvents().subscribe();
                return resp;
            }));
    }

    updateEvent(event: Event) {
        return this.http.put<Event>(this.eventUrl + '/update', JSON.stringify(event), this.httpOptions)
            .pipe(map((resp: any) => {
                this.fetchEvents().subscribe();
                return resp;
            }));
    }

    deleteEvent(event: Event) {
        return this.http.delete(this.eventUrl + '/delete/' + event.id, this.httpOptions)
            .pipe(map((resp: any) => {
                this.fetchEvents().subscribe();
                return resp;
            }));
    }

    filterEvents() {
        let filteredEvents: Event[] = this.events.filter(event => {
            return true &&
                this.checkEventStatus(event) &&
                this.checkEventType(event) &&
                this.checkTechnology(event);
        });

        this.setEvents(filteredEvents);
    }

    private checkEventStatus(event: Event): boolean {
        const eventStatus = this.enumObjectService.getEnumObject('eventStatus');
        const today: string = this.datePipe.transform(new Date(), 'yyyy-MM-dd');

        if (eventStatus && eventStatus != 'All') {
            return eventStatus == 'Completed' ?
                (today > event.endDate.toString()) :
                (today <= event.endDate.toString() && today >= event.startDate.toString());
        }

        return true;
    }

    private checkEventType(event: Event): boolean {
        const eventType = this.enumObjectService.getEnumObject('eventType');

        if (eventType && eventType != 'All') {
            return event.eventType.name == eventType;
        }

        return true;
    }

    private checkTechnology(event: Event): boolean {
        const technology = this.enumObjectService.getEnumObject('technology');

        if (technology && technology != 'All') {
            return (event.technologies.map(technology => technology.name)).indexOf(technology) !== -1;
        }

        return true;
    }

    private setEvents(events: Event[]): void {
        this.eventsChanged.next(events.slice());
    }

    private fetchEventById(id: number): Observable<Event> {
        return this.http
            .get<Event>(this.eventUrl + "/" + id)
            .pipe(
                take(1)
            );
    }

    private fetchEvents(): Observable<Event[]> {
        return this.http
            .get<Event[]>(this.eventUrl + "/all")
            .pipe(
                take(1),
                tap(events => {
                    this.events = events;
                    this.setEvents(events);
                }));
    }

}
