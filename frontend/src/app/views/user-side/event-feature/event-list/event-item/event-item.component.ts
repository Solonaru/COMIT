import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-event-item',
  templateUrl: './event-item.component.html',
  styleUrls: ['./event-item.component.css']
})
export class EventItemComponent implements OnInit {
  @Input() event: Event;
  @Input() index: number;

  constructor(private router: Router ) { }

  ngOnInit() {
  }

  goToPage() {
    this.router.navigate(['/challenges', this.index]);
  }

}
