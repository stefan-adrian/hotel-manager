import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AlimentsRoutingModule } from './aliments-routing.module';
import {SharedModule} from "../../shared/shared.module";
import { AlimentCreationComponent } from './components/aliment-creation/aliment-creation.component';
import { AlimentListComponent } from './components/aliment-list/aliment-list.component';

@NgModule({
  declarations: [AlimentCreationComponent, AlimentListComponent],
  imports: [
    SharedModule,
    CommonModule,
    AlimentsRoutingModule
  ]
})
export class AlimentsModule { }
