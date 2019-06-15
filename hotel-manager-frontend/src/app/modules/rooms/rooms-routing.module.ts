import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RoomListComponent} from "./components/room-list/room-list.component";
import {RoomDetailComponent} from "./components/room-detail/room-detail.component";
import {RoomCreationComponent} from "./components/room-creation/room-creation.component";
import {RoleGuard} from "../../core/guards/role-guard";

const routes: Routes = [
  {
    path: '',
    component: RoomListComponent,
    canActivate: [RoleGuard],
    data: {
      expectedRole: 'ROLE_ADMIN',
    }
  },
  {
    path: 'add',
    component: RoomCreationComponent,
    canActivate: [RoleGuard],
    data: {
      expectedRole: 'ROLE_ADMIN'
    }
  },
  {
    path: ':id',
    component: RoomDetailComponent,
    canActivate: [RoleGuard],
    data: {
      expectedRole: 'ROLE_ADMIN',
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RoomsRoutingModule { }
