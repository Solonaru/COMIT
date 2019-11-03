import { Routes } from '@angular/router';
import { HomeComponent } from 'src/views/home/home.component';
import { AboutComponent } from 'src/views/about/about.component';

export const appRoutes:Routes = [
    { path: '', component: HomeComponent },
    { path: 'about', component: AboutComponent },
]
