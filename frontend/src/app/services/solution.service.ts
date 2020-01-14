import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Solution } from '../models/solution.model';
import { map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class SolutionService {

    private solutionUrl = 'http://localhost:8080/solution';
    private httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    constructor(private http: HttpClient) { }

    addSolution(solution: Solution) {
        return this.http.post<Solution>(this.solutionUrl + '/add', JSON.stringify(solution), this.httpOptions)
            .pipe(map((resp: any) => {
                return resp;
            }));
    }

}