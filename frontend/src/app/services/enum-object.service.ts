import { Injectable } from '@angular/core';
import { EnumObject } from '../models/enum-object.model';
import { map, take } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class EnumObjectService {
    eventTypeChanged = new Subject<string>();
    technologyChanged = new Subject<string>();
    skillLevelChanged = new Subject<string>();
    eventStatusChanged = new Subject<string>();

    private eventType: string;
    private technology: string;
    private skillLevel: string;
    private eventStatus: string;

    private baseUrl = 'http://localhost:8080/';

    constructor(private http: HttpClient) { }

    setEnumObject(selector: string, value: string): void {
        switch (selector) {
            case 'eventType':
                this.setEventType(value);
                break;
            case 'technology':
                this.setTechnology(value);
                break;
            case 'skillLevel':
                this.setSkillLevel(value);
                break;
            case 'eventStatus':
                this.setEventStatus(value);
                break;
        }
    }

    getEnumObject(selector: string): string {
        switch (selector) {
            case 'eventType':
                return this.getEventType();
            case 'technology':
                return this.getTechnology();
            case 'skillLevel':
                return this.getSkillLevel();
            case 'eventStatus':
                return this.getEventStatus();
        }
    }

    getEnumObjects(selector: string): Observable<string[]> {
        return this.fetchEnumObjects(selector);
    }

    private getEventType(): string {
        return (this.eventType) ? this.eventType : null;
    }

    private getTechnology(): string {
        return (this.technology) ? this.technology : null;
    }

    private getSkillLevel(): string {
        return (this.skillLevel) ? this.skillLevel : null;
    }

    private getEventStatus(): string {
        return (this.eventStatus) ? this.eventStatus : null;
    }

    private setEventType(value: string): void {
        this.eventType = value;
        this.eventTypeChanged.next(value);
    }

    private setTechnology(value: string): void {
        this.technology = value;
        this.technologyChanged.next(value);
    }

    private setSkillLevel(value: string): void {
        this.skillLevel = value;
        this.skillLevelChanged.next(value);
    }

    private setEventStatus(value: string): void {
        this.eventStatus = value;
        this.eventStatusChanged.next(value);
    }

    private fetchEnumObjects(selector: string): Observable<string[]> {
        return this.http
            .get<EnumObject[]>(this.baseUrl + selector + "/all")
            .pipe(
                take(1),
                map(responseData => {
                    const arr: string[] = [];
                    arr.push("All");
                    responseData.forEach(function (value) {
                        arr.push(value.name);
                    });

                    return arr;
                }));
    }

}
