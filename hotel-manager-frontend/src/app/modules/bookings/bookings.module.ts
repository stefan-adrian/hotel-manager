import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {BookingsRoutingModule} from './bookings-routing.module';
import {SharedModule} from "../../shared/shared.module";
import { ConfirmationComponent } from './components/booking/tabs/confirmation/confirmation.component';

@NgModule({
  declarations: [ConfirmationComponent],
  imports: [
    CommonModule,
    SharedModule,
    BookingsRoutingModule
  ]
})
export class BookingsModule { }
