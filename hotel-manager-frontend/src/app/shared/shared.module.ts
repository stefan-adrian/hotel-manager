import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from "@angular/material";
import {ButtonModule} from 'primeng/button';
import {CheckboxModule} from 'primeng/checkbox';
import {FileUploadModule} from 'primeng/fileupload';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import {PanelModule} from "primeng/panel";
import {DataViewModule} from 'primeng/dataview';
import {SafeHtml} from "./pipes/safe-html";
import {DropdownModule} from "primeng/primeng";
import {InputTextModule} from "primeng/primeng";
import {MatIconModule} from '@angular/material/icon';
import {MatSelectModule} from '@angular/material/select';
import { FooterComponent } from './layout/footer/footer.component';

@NgModule({
  declarations: [SafeHtml],
  imports: [
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    ButtonModule,
    CheckboxModule,
    FileUploadModule,
    MessageModule,
    MessagesModule,
    PanelModule,
    DataViewModule,
    DropdownModule,
    InputTextModule,
    MatIconModule,
    MatSelectModule
  ],
  exports: [
    HttpClientModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    ButtonModule,
    CheckboxModule,
    FileUploadModule,
    MessageModule,
    MessagesModule,
    PanelModule,
    DataViewModule,
    DropdownModule,
    InputTextModule,
    MatIconModule,
    MatSelectModule,

    SafeHtml

  ]
})
export class SharedModule {
}
