import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { Challenge } from '../models/challenge.model';
import { HttpClient } from '@angular/common/http';
import { EnumObjectService } from './enum-object.service';
import { take, tap } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class ChallengeService {
    challengesChanged = new Subject<Challenge[]>();

    private challengeUrl = 'http://localhost:8080/challenge';
    private challenges: Challenge[] = [];

    constructor(
        private http: HttpClient,
        private enumObjectService: EnumObjectService) { }

    getChallengeById(id: number): Observable<Challenge> {
        return this.fetchChallengeById(id);
    }

    getChallenges(): void {
        this.fetchChallenges().subscribe(challenges => {
            console.log(challenges);
        });
    }

    getChallengesByEventId(eventId: number) {
        this.fetchChallengesByEventId(eventId).subscribe(challenges => {
            console.log(challenges);
        });
    }

    filterChallenges() {
        let filteredChallenges: Challenge[] = this.challenges.filter(challenge => {
            return true &&
                this.checkTechnology(challenge) &&
                this.checkSkillLevel(challenge);
        });

        this.setChallenges(filteredChallenges);
    }

    private checkTechnology(challenge: Challenge): boolean {
        const technology = this.enumObjectService.getEnumObject('technology');

        if (technology && technology != 'All') {
            return (challenge.technologies.map(technology => technology.name)).indexOf(technology) !== -1;
        }

        return true;
    }

    private checkSkillLevel(challenge: Challenge): boolean {
        const skillLevel = this.enumObjectService.getEnumObject('skillLevel');

        if (skillLevel && skillLevel != 'All') {
            return challenge.skillLevel.name == skillLevel;
        }

        return true;
    }

    private setChallenges(challenges: Challenge[]): void {
        this.challengesChanged.next(challenges.slice());
    }

    private fetchChallengeById(id: number): Observable<Challenge> {
        return this.http
            .get<Challenge>(this.challengeUrl + "/" + id)
            .pipe(
                take(1)
            );
    }

    private fetchChallenges(): Observable<Challenge[]> {
        return this.http
            .get<Challenge[]>(this.challengeUrl + "/all")
            .pipe(
                take(1),
                tap(challenges => {
                    this.challenges = challenges;
                    this.setChallenges(challenges);
                }));
    }

    private fetchChallengesByEventId(eventId: number): Observable<Challenge[]> {
        return this.http
            .get<Challenge[]>(this.challengeUrl + "/all" + "/" + eventId)
            .pipe(
                take(1),
                tap(challenges => {
                    this.challenges = challenges;
                    this.setChallenges(challenges);
                }));
    }

}
