import {Component, Inject, OnInit} from '@angular/core';
import {Aliment} from "../../../../core/models/aliment.model";
import {MAT_DIALOG_DATA} from "@angular/material";
import {DialogData} from "../aliment-list/aliment-list.component";

@Component({
  selector: 'app-cart-dialog',
  templateUrl: './cart-dialog.component.html',
  styleUrls: ['./cart-dialog.component.css']
})
export class CartDialogComponent implements OnInit {

  private aliments:Aliment[];
  private orderPrice: number;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) { }

  ngOnInit() {
    this.aliments=this.data.aliments;
    this.calculateOrderPrice();
  }

  private calculateOrderPrice():void{
    this.orderPrice=0;
    for(let aliment of this.aliments){
      this.orderPrice+=aliment.price;
    }
  }
  private order(): void{

  }

}
