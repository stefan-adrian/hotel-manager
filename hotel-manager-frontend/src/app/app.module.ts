import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {SharedModule} from "./shared/shared.module";
import {HeaderComponent} from "./shared/layout/header/header.component";
import {CoreModule} from "./core/core.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FooterComponent} from "./shared/layout/footer/footer.component";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    SharedModule,
    CoreModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
