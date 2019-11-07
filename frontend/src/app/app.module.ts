import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing-module';
import { SwiperModule } from 'ngx-swiper-wrapper';
import { SWIPER_CONFIG } from 'ngx-swiper-wrapper';
import { DEFAULT_SWIPER_CONFIG } from './swiper';
import { httpInterceptorProviders } from './components/auth/auth-interceptor';

import { AppComponent } from './app.component';
import { HomeComponent } from './views/home/home.component';
import { AltHomeComponent } from './views/alt-home/alt-home.component';
import { AboutComponent } from './views/about/about.component';
import { ContactComponent } from './views/contact/contact.component';

import { NavbarComponent } from './components/navbar/navbar.component';
import { SliderComponent } from './components/slider/slider.component';

import { EventsComponent } from './views/events/events.component';
import { EventComponent } from './views/event/event.component';
import { InstructionsComponent } from './views/event/instructions/instructions.component';
import { CodeComponent } from './views/event/code/code.component';
import { ResourcesComponent } from './views/event/resources/resources.component';
import { SolutionsComponent } from './views/event/solutions/solutions.component';

import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';
import { UserComponent } from './components/roles/user/user.component';
import { PmComponent } from './components/roles/pm/pm.component';
import { AdminComponent } from './components/roles/admin/admin.component';
import { DashboardComponent } from './views/dashboard/dashboard.component';
import { AccountManagerComponent } from './views/account-manager/account-manager.component';
import { EventManagerComponent } from './views/event-manager/event-manager.component';
import { FooterComponent } from './components/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AltHomeComponent,
    AboutComponent,
    ContactComponent,

    NavbarComponent,
    SliderComponent,

    EventsComponent,
    EventComponent,
    InstructionsComponent,
    CodeComponent,
    ResourcesComponent,
    SolutionsComponent,

    LoginComponent,
    RegisterComponent,
    UserComponent,
    PmComponent,
    AdminComponent,
    DashboardComponent,
    AccountManagerComponent,
    EventManagerComponent,
    FooterComponent,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
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
