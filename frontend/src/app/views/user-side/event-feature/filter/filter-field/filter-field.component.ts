import { Component, OnInit, Input } from '@angular/core';
import { EnumObjectService } from 'src/app/services/enum-object.service';

@Component({
  selector: 'app-filter-field',
  templateUrl: './filter-field.component.html',
  styleUrls: ['./filter-field.component.css']
})
export class FilterFieldComponent implements OnInit {
  @Input() filterField: string;
  @Input() index: number;
  enumObjects : string[] = [];

  constructor(private enumObjectService: EnumObjectService) { }

  ngOnInit() {
    this.enumObjectService.getEnumObjects(this.filterField).subscribe(data => {
      this.enumObjects = data;
    });
  }

  onSelect(option: string) {
    this.enumObjectService.setEnumObject(this.filterField, option);
  }

}
