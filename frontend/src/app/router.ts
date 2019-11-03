import { Routes } from '@angular/router';
import { HomeComponent } from 'src/views/home/home.component';
import { AboutComponent } from 'src/views/about/about.component';
import { ContactComponent } from 'src/views/contact/contact.component';

export const appRoutes:Routes = [
    { path: '', component: HomeComponent },
    { path: 'about', component: AboutComponent },
    { path: 'contact', component: ContactComponent },
]
