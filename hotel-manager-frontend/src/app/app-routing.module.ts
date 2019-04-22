import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

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
