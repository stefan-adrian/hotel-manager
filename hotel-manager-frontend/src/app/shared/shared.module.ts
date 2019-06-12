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
import {DropdownModule, InputTextModule} from "primeng/primeng";
import {MatIconModule} from '@angular/material/icon';
import {MatSelectModule} from '@angular/material/select';
import {MatTabsModule} from '@angular/material/tabs';
import {CalendarModule} from 'primeng/calendar';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatBadgeModule} from '@angular/material/badge';
import {MatDialogModule} from '@angular/material/dialog';

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
    MatTabsModule,
    CalendarModule,
    MatSidenavModule,
    MatListModule,
    MatCardModule,
    MatButtonModule,
    MatBadgeModule,
    MatDialogModule
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
    MatTabsModule,
    CalendarModule,
    MatSidenavModule,
    MatListModule,
    MatCardModule,
    MatButtonModule,
    MatBadgeModule,
    MatDialogModule,

    SafeHtml

  ]
})
export class SharedModule {
}
