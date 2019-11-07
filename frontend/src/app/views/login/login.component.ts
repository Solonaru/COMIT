import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthLoginInfo } from '../../components/auth/login-info';
import { AuthService } from '../../components/auth/auth.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent {

    private loginInfo: AuthLoginInfo;
    isLoginFailed = false;
    errorMessage: string = null;

    constructor(
        private authService: AuthService
    ) { }

    ngOnInit() { }

    onSubmit(form: NgForm) {
        if (!form.valid) {
            return;
        }

        this.loginInfo = new AuthLoginInfo(form.value.username, form.value.password);

        this.authService.attemptAuth(this.loginInfo).subscribe(
            data => { },
            error => {
                this.errorMessage = error;
                this.isLoginFailed = true;
            }
        );

        form.reset();
    }

}