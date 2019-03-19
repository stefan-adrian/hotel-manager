import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import {MatCheckboxModule} from '@angular/material/checkbox';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatCheckboxModule
  ],
  exports: [
    HttpClientModule,
    ReactiveFormsModule,
    MatCheckboxModule
  ]
})
export class SharedModule {
}
