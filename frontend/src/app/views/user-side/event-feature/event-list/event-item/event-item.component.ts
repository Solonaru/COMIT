import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Event } from 'src/app/models/event.model';

@Component({
  selector: 'app-event-item',
  templateUrl: './event-item.component.html',
  styleUrls: ['./event-item.component.css']
})
export class EventItemComponent implements OnInit {
  @Input() event: Event;

  constructor(private router: Router ) { }

  ngOnInit() {
  }

  goToPage() {
    this.router.navigate(['/challenges', this.event.id]);
  }

}
