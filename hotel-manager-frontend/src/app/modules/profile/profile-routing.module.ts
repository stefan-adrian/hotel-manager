import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProfilePageComponent} from "./components/profile-page/profile-page.component";
import {AccountBookingsComponent} from "./components/account-bookings/account-bookings.component";

const routes: Routes = [
  {
    path: '',
    component: ProfilePageComponent
  },
  {
    path: 'bookings',
    component: AccountBookingsComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfileRoutingModule { }
