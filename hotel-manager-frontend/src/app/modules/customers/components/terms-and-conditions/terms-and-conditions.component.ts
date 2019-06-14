import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from "@angular/material";

@Component({
  selector: 'app-terms-and-conditions',
  templateUrl: './terms-and-conditions.component.html',
  styleUrls: ['./terms-and-conditions.component.css']
})
export class TermsAndConditionsComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<TermsAndConditionsComponent>
  ) { }

  ngOnInit(
  ) {
  }

  private cancelDialog(): void{
    this.dialogRef.close();
  }

}
