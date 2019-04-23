import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AlimentCreationComponent} from "./components/aliment-creation/aliment-creation.component";
import {AlimentListComponent} from "./components/aliment-list/aliment-list.component";
import {AuthenticationGuard} from "../../core/guards/authentication-guard";
import {RoleGuard} from "../../core/guards/role-guard";

const routes: Routes = [
  {
    path: '',
    component: AlimentListComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: 'add',
    component: AlimentCreationComponent,
    canActivate: [RoleGuard],
    data: {
      expectedRole: 'ROLE_ADMIN'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AlimentsRoutingModule { }
