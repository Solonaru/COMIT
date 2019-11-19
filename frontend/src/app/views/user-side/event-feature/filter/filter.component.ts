import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {
  eventTypes: string[];
  technologies: string[];
  @Input() filterFields: string[];
  @Input() filterTitle: string;

  constructor() { }

  ngOnInit() { }

}
