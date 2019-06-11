import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CategoriesRoutingModule } from './categories-routing.module';
import {SharedModule} from "../../shared/shared.module";
import { CategoryCreationComponent } from './components/category-creation/category-creation.component';
import { CategoryListComponent } from './components/category-list/category-list.component';
import { CategoryDetailsComponent } from './components/category-details/category-details.component';

@NgModule({
  declarations: [CategoryCreationComponent, CategoryListComponent, CategoryDetailsComponent],
  imports: [
    SharedModule,
    CommonModule,
    CategoriesRoutingModule
  ]
})
export class CategoriesModule { }
