import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-challenge-manager',
  templateUrl: './challenge-manager.component.html',
  styleUrls: ['./challenge-manager.component.css']
})
export class ChallengeManagerComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  limitLength(string) {
    if (string.length > 150) return string.substring(1, 150) + ' ...'
    return string
  }
}
