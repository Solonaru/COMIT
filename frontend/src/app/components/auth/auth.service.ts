import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, BehaviorSubject, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

import { JwtResponse } from './jwt-response';
import { AuthLoginInfo } from './login-info';
import { SignUpInfo } from './signup-info';
import { User } from './user.model';
import { TokenStorageService } from './token-storage.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  user = new BehaviorSubject<User>(null);

  private loginUrl = 'http://localhost:8080/api/auth/signin';
  private signupUrl = 'http://localhost:8080/api/auth/signup';

  constructor(
    private http: HttpClient,
    private router: Router,
    private tokenStorageService: TokenStorageService
  ) { }

  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(
      this.loginUrl, credentials, httpOptions
    ).pipe(catchError(this.handleError), tap(data => {
      this.handleAuthentication(
        data.username,
        data.authorities,
        data.accessToken
      );
    }));
  }

  autoLogin() {
    if (
      !this.tokenStorageService.getUsername() ||
      !this.tokenStorageService.getToken()
    ) {
      return;
    }

    const loadedUser: User = {
      username: this.tokenStorageService.getUsername(),
      authorities: this.tokenStorageService.getAuthorities(),
      token: this.tokenStorageService.getToken()
    }

    this.user.next(loadedUser);
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(
      this.signupUrl, info, httpOptions
    ).pipe(catchError(this.handleError), tap(data => {
      this.handleRegistration();
    }));
  }

  logout() {
    this.user.next(null);
    this.tokenStorageService.clearTokenStorage();
    this.router.navigate(['/auth/login']);
  }

  private handleAuthentication(username: string, authorities: string[], accessToken: string) {
    const user = new User(
      username,
      authorities,
      accessToken
    );

    this.user.next(user);

    this.tokenStorageService.saveUsername(username);
    this.tokenStorageService.saveAuthorities(authorities);
    this.tokenStorageService.saveToken(accessToken);

    this.router.navigate(['/home']);
  }

  private handleRegistration() {
    this.router.navigate(['/auth/login']);
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An unknown error occurred!';

    if (!error.error) {
      return throwError(errorMessage);
    }

    switch (error.error.message) {
      case 'EMAIL_EXISTS':
        errorMessage = 'This email exists already!';
        break;
      case 'USERNAME_EXISTS':
        errorMessage = 'This username exists already!';
        break;
      case 'INVALID_CREDENTIALS':
        errorMessage = 'Invalid credentials!';
        break;
    }

    return throwError(errorMessage);
  }

}
