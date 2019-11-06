import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  open: Boolean = false;
  constructor() { }

  ngOnInit() {
  }

  toggleMenu () {
    this.open = !this.open;
  };
}
