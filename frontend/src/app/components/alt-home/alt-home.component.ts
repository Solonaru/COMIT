import { Component, OnInit } from '@angular/core';

import { TokenStorageService } from '../auth/token-storage.service';

@Component({
    selector: 'app-alt-home',
    templateUrl: './alt-home.component.html',
    styleUrls: ['./alt-home.component.css']
})
export class AltHomeComponent implements OnInit {
    info: any;

    constructor(private token: TokenStorageService) { }

    ngOnInit() {
        this.info = {
            token: this.token.getToken(),
            username: this.token.getUsername(),
            authorities: this.token.getAuthorities()
        };
    }

    logout() {
        this.token.signOut();
        window.location.reload();
    }
}
