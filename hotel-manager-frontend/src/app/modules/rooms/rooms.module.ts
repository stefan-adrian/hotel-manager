import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RoomsRoutingModule } from './rooms-routing.module';
import { RoomListComponent } from './components/room-list/room-list.component';
import {SharedModule} from "../../shared/shared.module";
import { RoomDetailComponent } from './components/room-detail/room-detail.component';

@NgModule({
  declarations: [RoomListComponent, RoomDetailComponent],
  imports: [
    SharedModule,
    CommonModule,
    RoomsRoutingModule
  ]
})
export class RoomsModule { }
