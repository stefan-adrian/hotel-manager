import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BookingComponent} from "./modules/bookings/components/booking/booking.component";
import {DatesComponent} from "./modules/bookings/components/booking/tabs/dates/dates.component";
import {RoomsComponent} from "./modules/bookings/components/booking/tabs/rooms/rooms.component";
import {ConfirmationComponent} from "./modules/bookings/components/booking/tabs/confirmation/confirmation.component";
import {AuthenticationGuard} from "./core/guards/authentication-guard";

const routes: Routes = [
  {
    path: 'rooms',
    loadChildren: './modules/rooms/rooms.module#RoomsModule'
  },
  {
    path: 'aliments',
    loadChildren: './modules/aliments/aliments.module#AlimentsModule'
  },
  {
    path: 'customers',
    loadChildren: './modules/customers/customers.module#CustomersModule'
  },
  {
    path: 'home',
    loadChildren: './modules/home/home.module#HomeModule'
  },
  {
    path: 'login',
    loadChildren: './modules/authentication/authentication.module#AuthenticationModule'
  },
  {
    path: 'categories',
    loadChildren: './modules/categories/categories.module#CategoriesModule'
  },
  {
    path: 'profile',
    loadChildren: './modules/profile/profile.module#ProfileModule'
  },
  {
    path: 'bookings',
    component: BookingComponent,
    children: [
      {path: 'dates', component: DatesComponent, outlet: 'sub'},
      {path: 'rooms', component: RoomsComponent, outlet: 'sub'},
      {path: 'confirmation', component: ConfirmationComponent, outlet: 'sub'}

    ],
    canActivate: [AuthenticationGuard]
  },
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
