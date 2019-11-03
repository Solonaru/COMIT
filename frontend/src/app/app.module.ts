import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { appRoutes } from './router';
import { SwiperModule } from 'ngx-swiper-wrapper';
import { SWIPER_CONFIG } from 'ngx-swiper-wrapper';
import { DEFAULT_SWIPER_CONFIG } from './swiper'

import { AppComponent } from './app.component';
import { NavbarComponent } from './../components/navbar/navbar.component';
import { SliderComponent } from './../components/slider/slider.component';
import { HomeComponent } from './../views/home/home.component';
import { AboutComponent } from './../views/about/about.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SliderComponent,
    HomeComponent,
    AboutComponent
  ],
  imports: [
    BrowserModule,
    SwiperModule,
    RouterModule.forRoot(
      appRoutes
    )
  ],
  providers: [{
    provide: SWIPER_CONFIG,
    useValue: DEFAULT_SWIPER_CONFIG
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
