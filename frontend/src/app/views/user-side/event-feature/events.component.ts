import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EnumObjectService } from 'src/app/services/enum-object.service';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {
  eventTypes: string[];
  technologies: string[];

  constructor(private enumObjectService: EnumObjectService) { }

  ngOnInit() {
    this.enumObjectService.getEventTypes().subscribe(data => {
      this.eventTypes = data;
    });

    this.enumObjectService.getTechnologies().subscribe(data => {
      this.technologies = data;
    });
  }

}
