import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RoomListComponent} from "./components/room-list/room-list.component";
import {RoomDetailComponent} from "./components/room-detail/room-detail.component";
import {RoomCreationComponent} from "./components/room-creation/room-creation.component";

const routes: Routes = [
  {
    path: '',
    component: RoomListComponent
  },
  {
    path: ':id',
    component: RoomDetailComponent
  },
  {
    path: 'add',
    component: RoomCreationComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RoomsRoutingModule { }
