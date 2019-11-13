import { Injectable } from '@angular/core';
import { EnumObject } from '../models/enum-object.model';
import { map, take } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class EnumObjectService {

    private eventTypeUrl = 'http://localhost:8080/eventType';
    private technologyUrl = 'http://localhost:8080/technology';
    private skillLevelUrl = 'http://localhost:8080/skillLevel';

    constructor(private http: HttpClient) { }

    getEventTypes(): Observable<string[]> {
        return this.fetchEnumObjects(this.eventTypeUrl);
    }

    getTechnologies(): Observable<string[]> {
        return this.fetchEnumObjects(this.technologyUrl);
    }

    getSkillLevels(): Observable<string[]> {
        return this.fetchEnumObjects(this.skillLevelUrl);
    }

    private fetchEnumObjects(enumObjectUrl: string) {
        return this.http
            .get<EnumObject[]>(enumObjectUrl + "/all")
            .pipe(
                take(1),
                map(responseData => {
                    const arr: string[] = [];
                    responseData.forEach(function (value) {
                        arr.push(value.name);
                    });

                    return arr;
                }));
    }

}
