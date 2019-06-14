import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfileRoutingModule } from './profile-routing.module';
import { ProfilePageComponent } from './components/profile-page/profile-page.component';
import {SharedModule} from "../../shared/shared.module";
import { AccountBookingsComponent } from './components/account-bookings/account-bookings.component';
import { AccountRoomservicesComponent } from './components/account-roomservices/account-roomservices.component';
import { AllRoomservicesComponent } from './components/all-roomservices/all-roomservices.component';

@NgModule({
  declarations: [ProfilePageComponent, AccountBookingsComponent, AccountRoomservicesComponent, AllRoomservicesComponent],
  imports: [
    CommonModule,
    SharedModule,
    ProfileRoutingModule
  ]
})
export class ProfileModule { }
