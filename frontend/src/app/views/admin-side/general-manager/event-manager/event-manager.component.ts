import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-event-manager',
  templateUrl: './event-manager.component.html',
  styleUrls: ['./event-manager.component.css']
})
export class EventManagerComponent implements OnInit {
  modal: Boolean = false;
  selectedAction: String = '';

  constructor() { }

  ngOnInit() {
  }

  toggleModal(e, action) {
    e.preventDefault();
    this.modal = !this.modal;

    if(action) this.selectedAction = action;
  }

}
