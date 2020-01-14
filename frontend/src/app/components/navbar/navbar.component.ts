import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { Subscription } from 'rxjs';
import { User } from '../auth/user.model';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, OnDestroy {
  private userSub: Subscription;
  isAuthenticated: boolean = false;
  isAdmin: boolean = false;
  open: boolean = false;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.userSub = this.authService.user.subscribe((user: User) => {
      this.checkStatus(user);
    });
  }

  toggleMenu() {
    this.open = !this.open;
  }

  onLogout() {
    this.checkStatus(null);
    this.authService.logout();
  }

  ngOnDestroy() {
    this.userSub.unsubscribe();
  }

  private checkStatus(user: User) {
    this.isAuthenticated = !!user;

    if (this.isAuthenticated) {
      let auths: string[];

      if (this.checkIfAllString(user.authorities)) {
        auths = user.authorities;
      } else {
        auths = user.authorities.reduce(function (s, a) {
          s.push(a["authority"]);
          return s;
        }, []);
      }

      this.isAdmin = -1 != auths.indexOf("ROLE_ADMIN");
    } else {
      this.isAdmin = false;
    }

  }

  private checkIfAllString(x) {
    return x.every(function (i) { return typeof i === "string" });
  }

}
