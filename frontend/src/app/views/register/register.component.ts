import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../../components/auth/auth.service';
import { TokenStorageService } from '../../components/auth/token-storage.service';
import { SignUpInfo } from '../../components/auth/signup-info';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

    signupInfo: SignUpInfo;
    isLoggedIn = false;
    isSignedUp = false;
    isSignUpFailed = false;
    errorMessage = '';
    roles: string[] = [];

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

        this.signupInfo = new SignUpInfo(
            form.value.name,
            form.value.surname,
            form.value.username,
            form.value.email,
            form.value.password);

        console.log(this.signupInfo);

        this.authService.signUp(this.signupInfo).subscribe(
            data => {
                console.log(data);
                this.isSignedUp = true;
                this.isSignUpFailed = false;
            },
            error => {
                console.log(error);
                this.errorMessage = error.error.message;
                this.isSignUpFailed = true;
            }
        );

        form.reset();
    }

}