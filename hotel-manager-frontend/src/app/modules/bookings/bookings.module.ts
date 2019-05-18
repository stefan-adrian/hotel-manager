import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {BookingsRoutingModule} from './bookings-routing.module';
import {SharedModule} from "../../shared/shared.module";

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    SharedModule,
    BookingsRoutingModule
  ]
})
export class BookingsModule { }
