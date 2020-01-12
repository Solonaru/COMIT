import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class UtilityService {

    private baseUrl = 'http://localhost:8080';

    constructor(private http: HttpClient) { }

    getUsersCount(): Observable<number> {
        return this.http
            .get<number>(this.baseUrl + "/usr/all/count")
            .pipe(take(1));
    }

    getEventsCount(): Observable<number> {
        return this.http
            .get<number>(this.baseUrl + "/event/all/count")
            .pipe(take(1));
    }

    getChallengesCount(): Observable<number> {
        return this.http
            .get<number>(this.baseUrl + "/challenge/all/count")
            .pipe(take(1));
    }

}
