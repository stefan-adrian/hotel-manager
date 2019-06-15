import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProfilePageComponent} from "./components/profile-page/profile-page.component";
import {AccountBookingsComponent} from "./components/account-bookings/account-bookings.component";
import {AccountRoomservicesComponent} from "./components/account-roomservices/account-roomservices.component";
import {AllRoomservicesComponent} from "./components/all-roomservices/all-roomservices.component";
import {AllBookingsComponent} from "./components/all-bookings/all-bookings.component";
import {AlimentCreationComponent} from "../aliments/components/aliment-creation/aliment-creation.component";
import {RoleGuard} from "../../core/guards/role-guard";

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
  },
  {
    path: 'all-orders',
    component: AllRoomservicesComponent
  },
  {
    path: 'all-bookings',
    component: AllBookingsComponent,
    canActivate: [RoleGuard],
    data: {
      expectedRole: 'ROLE_EMPLOYEE'
    }
  }



];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfileRoutingModule { }
