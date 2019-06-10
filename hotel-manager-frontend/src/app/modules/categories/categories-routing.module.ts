import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RoleGuard} from "../../core/guards/role-guard";
import {CategoryCreationComponent} from "./components/category-creation/category-creation.component";
import {CategoryListComponent} from "./components/category-list/category-list.component";
import {CategoryDetailsComponent} from "./components/category-details/category-details.component";

const routes: Routes = [
  {
    path: '',
    component: CategoryListComponent
  },
  {
    path: 'add',
    component: CategoryCreationComponent,
    canActivate: [RoleGuard],
    data: {
      expectedRole: 'ROLE_ADMIN'
    }
  },
  {
    path: ':id',
    component: CategoryDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CategoriesRoutingModule { }
