import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UtilityService } from 'src/app/services/utility.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  usersCount: number;
  eventsCount: number;
  challengesCount: number;

  constructor(
    private router: Router,
    private utilityService: UtilityService) { }

  ngOnInit() {
    this.utilityService.getUsersCount().subscribe(count => {
      this.usersCount = count;
    });

    this.utilityService.getEventsCount().subscribe(count => {
      this.eventsCount = count;
    });

    this.utilityService.getChallengesCount().subscribe(count => {
      this.challengesCount = count;
    });
  }

  goToAccounts() {
    this.router.navigateByUrl('/account-manager');
  }

  goToEvents() {
    this.router.navigateByUrl('/event-manager');
  }

  goToGeneralManager() {
    this.router.navigate(['../general-manager']);
  }

}
