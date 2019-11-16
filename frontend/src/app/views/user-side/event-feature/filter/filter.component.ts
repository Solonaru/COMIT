import { Component, OnInit, Input } from '@angular/core';
import { EnumObjectService } from 'src/app/services/enum-object.service';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {
  eventTypes: string[];
  technologies: string[];
  @Input() filterFields: string[];

  constructor() { }

  ngOnInit() { }

}
