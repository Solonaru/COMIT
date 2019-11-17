import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Challenge } from 'src/app/models/challenge.model';

@Component({
  selector: 'app-challenge-item',
  templateUrl: './challenge-item.component.html',
  styleUrls: ['./challenge-item.component.css']
})
export class ChallengeItemComponent implements OnInit {
  @Input() challenge: Challenge;
  @Input() index: number;

  constructor(private router: Router) { }

  ngOnInit() {
  }

  goToPage() {
    this.router.navigate(['/challenge', '1'])
  }

}
