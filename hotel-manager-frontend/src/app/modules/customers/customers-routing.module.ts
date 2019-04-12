import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CustomerCreationComponent} from "./components/customer-creation/customer-creation.component";


const routes: Routes = [
  {
    path: 'add',
    component: CustomerCreationComponent
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomersRoutingModule { }
