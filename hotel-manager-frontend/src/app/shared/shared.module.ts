import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatCheckboxModule} from '@angular/material/checkbox';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    FormsModule
  ],
  exports: [
    HttpClientModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    FormsModule
  ]
})
export class SharedModule {
}
