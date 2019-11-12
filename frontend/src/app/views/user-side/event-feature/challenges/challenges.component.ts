import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-challenges',
  templateUrl: './challenges.component.html',
  styleUrls: ['./challenges.component.css']
})
export class ChallengesComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  goToPage () {
    this.router.navigate(['/challenge', '1'])
  }
}
