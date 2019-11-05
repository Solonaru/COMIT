import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthLoginInfo } from '../../components/auth/login-info';
import { AuthService } from '../../components/auth/auth.service';
import { TokenStorageService } from '../../components/auth/token-storage.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent {

    isLoggedIn = false;
    isLoginFailed = false;
    errorMessage = '';
    roles: string[] = [];
    private loginInfo: AuthLoginInfo;

    constructor(
        private authService: AuthService,
        private tokenStorage: TokenStorageService
    ) {}

    ngOnInit() {
        if (this.tokenStorage.getToken()) {
            this.isLoggedIn = true;
            this.roles = this.tokenStorage.getAuthorities();
        }
    }

    onSubmit(form: NgForm) {
        if (!form.valid) {
            return;
        }

        this.loginInfo = new AuthLoginInfo(form.value.username, form.value.password);

        this.authService.attemptAuth(this.loginInfo).subscribe(
            data => {
                this.tokenStorage.saveToken(data.accessToken);
                this.tokenStorage.saveUsername(data.username);
                this.tokenStorage.saveAuthorities(data.authorities);

                this.isLoginFailed = false;
                this.isLoggedIn = true;
                this.roles = this.tokenStorage.getAuthorities();
            },
            error => {
                console.log(error);
                this.errorMessage = error.error.message;
                this.isLoginFailed = true;
            }
        );

        form.reset();
    }

}