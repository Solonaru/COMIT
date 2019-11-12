import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing-module';
import { SwiperModule } from 'ngx-swiper-wrapper';
import { SWIPER_CONFIG } from 'ngx-swiper-wrapper';
import { DEFAULT_SWIPER_CONFIG } from './swiper';
import { httpInterceptorProviders } from './components/auth/auth-interceptor.service';

import { AppComponent } from './app.component';
import { HomeComponent } from './views/user-side/presentation-feature/home/home.component';
import { AltHomeComponent } from './views/user-side/presentation-feature/alt-home/alt-home.component';
import { AboutComponent } from './views/user-side/presentation-feature/about/about.component';
import { ContactComponent } from './views/user-side/presentation-feature/contact/contact.component';

import { NavbarComponent } from './components/navbar/navbar.component';

import { EventsComponent } from './views/user-side/event-feature/events.component';

import { ChallengesComponent } from './views/user-side/event-feature/challenges/challenges.component';
import { ChallengeComponent } from './views/user-side/event-feature/challenges/challenge/challenge.component';
import { InstructionsComponent } from './views/user-side/event-feature/challenges/challenge/instructions/instructions.component';
import { CodeComponent } from './views/user-side/event-feature/challenges/challenge/code/code.component';
import { ResourcesComponent } from './views/user-side/event-feature/challenges/challenge/resources/resources.component';
import { SolutionsComponent } from './views/user-side/event-feature/challenges/challenge/solutions/solutions.component';

import { LoginComponent } from './views/user-side/auth-feature/login/login.component';
import { RegisterComponent } from './views/user-side/auth-feature/register/register.component';
import { UserComponent } from './components/roles/user/user.component';
import { PmComponent } from './components/roles/pm/pm.component';
import { AdminComponent } from './components/roles/admin/admin.component';
import { DashboardComponent } from './views/admin-side/dashboard/dashboard.component';
import { AccountManagerComponent } from './views/admin-side/account-manager/account-manager.component';
import { EventManagerComponent } from './views/admin-side/event-manager/event-manager.component';
import { FooterComponent } from './components/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AltHomeComponent,
    AboutComponent,
    ContactComponent,

    NavbarComponent,

    EventsComponent,

    ChallengesComponent,
    ChallengeComponent,
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
