import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  contactForm: FormGroup;
  
  constructor(private formBuilder: FormBuilder) {
  }
  createContactForm() {
    this.contactForm = this.formBuilder.group({
      fullName: [''],
      email: [''],
      message: ['']
    });
  }
  onSubmit() {
    console.log('Your form data : ', this.contactForm.value );
  }
  ngOnInit() {
    this.createContactForm();
  }

}
