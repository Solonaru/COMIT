import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { take, map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class EventService {

    private eventUrl = 'http://localhost:8080/event';

    constructor(private http: HttpClient) { }

    getEvents(): Observable<Event[]> {
        return this.http
            .get<Event[]>(this.eventUrl + "/all")
            .pipe(
                take(1),
                map(responseData => {
                    return responseData;
                }));
    }

}
