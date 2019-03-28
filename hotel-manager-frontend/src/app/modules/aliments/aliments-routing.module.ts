import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AlimentCreationComponent} from "./components/aliment-creation/aliment-creation.component";
import {AlimentListComponent} from "./components/aliment-list/aliment-list.component";

const routes: Routes = [
  {
    path: '',
    component: AlimentListComponent
  },
  {
    path: 'add',
    component: AlimentCreationComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AlimentsRoutingModule { }
