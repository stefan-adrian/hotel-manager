import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AlimentCreationComponent} from "./components/aliment-creation/aliment-creation.component";

const routes: Routes = [
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
