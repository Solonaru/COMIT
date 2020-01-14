import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SignUpInfo } from 'src/app/components/auth/signup-info';
import { AuthService } from 'src/app/components/auth/auth.service';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

    signupInfo: SignUpInfo;
    isSignUpFailed = false;
    errorMessage: string = null;

    constructor(
        private authService: AuthService
    ) { }

    ngOnInit() { }

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

        this.authService.signUp(this.signupInfo).subscribe(
            data => { },
            error => {
                this.errorMessage = error;
                this.isSignUpFailed = true;
            }
        );

        form.reset();
    }

}