import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AlimentsRoutingModule} from './aliments-routing.module';
import {SharedModule} from "../../shared/shared.module";
import {AlimentCreationComponent} from './components/aliment-creation/aliment-creation.component';
import {AlimentListComponent} from './components/aliment-list/aliment-list.component';
import { CartDialogComponent } from './components/cart-dialog/cart-dialog.component';

@NgModule({
  declarations: [AlimentCreationComponent, AlimentListComponent, CartDialogComponent],
  entryComponents: [
    CartDialogComponent
  ],
  imports: [
    SharedModule,
    CommonModule,
    AlimentsRoutingModule
  ]
})
export class AlimentsModule { }
