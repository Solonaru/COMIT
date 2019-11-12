import { Component, OnInit, OnDestroy } from '@angular/core';

import { AuthService } from 'src/app/components/auth/auth.service';
import { Subscription } from 'rxjs';

@Component({
    selector: 'app-alt-home',
    templateUrl: './alt-home.component.html',
    styleUrls: ['./alt-home.component.css']
})
export class AltHomeComponent implements OnInit, OnDestroy {
    private userSub: Subscription;
    token: string;
    isAuthenticated = false;

    constructor(private authService: AuthService) { }

    ngOnInit() {
        this.userSub = this.authService.user.subscribe(user => {
            if (user) {
                this.isAuthenticated = true;
                this.token = user.token;
            }
        });
    }

    logout() {
        this.authService.logout();
    }

    ngOnDestroy() {
        this.userSub.unsubscribe();   
    }
}
