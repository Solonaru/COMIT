import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { SwiperModule } from 'ngx-swiper-wrapper';
import { SWIPER_CONFIG } from 'ngx-swiper-wrapper';
import { DEFAULT_SWIPER_CONFIG } from './swiper'
import { FormsModule,  ReactiveFormsModule  }  from  '@angular/forms';
import { AppComponent } from './app.component';
import { NavbarComponent } from './../components/navbar/navbar.component';
import { SliderComponent } from './../components/slider/slider.component';
import { HomeComponent } from './../views/home/home.component';
import { AboutComponent } from './../views/about/about.component';
import { EventsComponent } from './../views/events/events.component';
import { EventComponent } from '../views/event/event.component';
import { InstructionsComponent } from '../views/event/instructions/instructions.component';
import { CodeComponent } from '../views/event/code/code.component';
import { ResourcesComponent } from '../views/event/resources/resources.component';
import { SolutionsComponent } from '../views/event/solutions/solutions.component';
import { ContactComponent } from '../views/contact/contact.component';

import { MobileMenuDirective } from './../shared/mobile-menu.directive';
import { LoginComponent } from './components/login/login.component';
import { UserComponent } from './components/roles/user/user.component';
import { RegisterComponent } from './components/register/register.component';
import { AltHomeComponent } from './components/alt-home/alt-home.component';
import { AdminComponent } from './components/roles/admin/admin.component';
import { PmComponent } from './components/roles/pm/pm.component';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing-module';
import { httpInterceptorProviders } from './components/auth/auth-interceptor';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SliderComponent,
    HomeComponent,
    AboutComponent,
    ContactComponent,
    MobileMenuDirective,
    AboutComponent,
    EventsComponent,
    EventComponent,
    CodeComponent,
    ResourcesComponent,
    SolutionsComponent,

    LoginComponent,
    UserComponent,
    RegisterComponent,
    HomeComponent,
    AltHomeComponent,
    AdminComponent,
    PmComponent,
    InstructionsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    SwiperModule,
    HttpClientModule,
    AppRoutingModule,
    SwiperModule
  ],
  providers: [{
    provide: SWIPER_CONFIG,
    useValue: DEFAULT_SWIPER_CONFIG
  },
    httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
