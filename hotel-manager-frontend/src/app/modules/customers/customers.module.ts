import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomersRoutingModule } from './customers-routing.module';
import { CustomerCreationComponent } from './components/customer-creation/customer-creation.component';
import {SharedModule} from "../../shared/shared.module";

@NgModule({
  declarations: [CustomerCreationComponent],
  imports: [
    SharedModule,
    CommonModule,
    CustomersRoutingModule
  ]
})
export class CustomersModule { }
