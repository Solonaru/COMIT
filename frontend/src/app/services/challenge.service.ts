import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { Challenge } from '../models/challenge.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EnumObjectService } from './enum-object.service';
import { take, tap, map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class ChallengeService {
    challengesChanged = new Subject<Challenge[]>();

    private challengeUrl = 'http://localhost:8080/challenge';
    private httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    private challenges: Challenge[] = [];

    constructor(
        private http: HttpClient,
        private enumObjectService: EnumObjectService) { }

    getChallengeById(id: number): Observable<Challenge> {
        return this.fetchChallengeById(id);
    }

    getChallenges(): void {
        this.fetchChallenges().subscribe();
    }

    getChallengesByEventId(eventId: number) {
        this.fetchChallengesByEventId(eventId).subscribe();
    }

    addChallenge(challenge: Challenge) {
        return this.http.post<Event>(this.challengeUrl + '/add', JSON.stringify(challenge), this.httpOptions)
            .pipe(map((resp: any) => {
                this.fetchChallenges().subscribe();
                return resp;
            }));
    }

    updateChallenge(challenge: Challenge) {
        return this.http.put<Event>(this.challengeUrl + '/update', JSON.stringify(challenge), this.httpOptions)
            .pipe(map((resp: any) => {
                this.fetchChallenges().subscribe();
                return resp;
            }));
    }

    deleteChallenge(challenge: Challenge) {
        return this.http.delete(this.challengeUrl + '/delete/' + challenge.id, this.httpOptions)
            .pipe(map((resp: any) => {
                this.fetchChallenges().subscribe();
                return resp;
            }));
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
