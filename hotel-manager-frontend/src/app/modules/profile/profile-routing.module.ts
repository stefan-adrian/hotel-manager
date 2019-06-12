import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProfilePageComponent} from "./components/profile-page/profile-page.component";
import {AccountBookingsComponent} from "./components/account-bookings/account-bookings.component";
import {AccountRoomservicesComponent} from "./components/account-roomservices/account-roomservices.component";

const routes: Routes = [
  {
    path: '',
    component: ProfilePageComponent
  },
  {
    path: 'bookings',
    component: AccountBookingsComponent
  },
  {
    path: 'orders',
    component: AccountRoomservicesComponent
  }


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfileRoutingModule { }
