import {Component, Inject, OnInit} from '@angular/core';
import {Aliment} from "../../../../core/models/aliment.model";
import {MAT_DIALOG_DATA} from "@angular/material";
import {DialogData} from "../aliment-list/aliment-list.component";
import {Booking} from "../../../../core/models/booking.model";
import {BookingService} from "../../../../core/services/booking.service";
import {RoomserviceCreation} from "../../../../core/models/roomservice-creation.model";
import {RoomserviceService} from "../../../../core/services/roomservice.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-cart-dialog',
  templateUrl: './cart-dialog.component.html',
  styleUrls: ['./cart-dialog.component.css']
})
export class CartDialogComponent implements OnInit {

  private aliments:Aliment[];
  private orderPrice: number;
  private selectedBooking:Booking;
  private bookings: Booking[];

  constructor(
    private bookingService: BookingService,
    private roomserviceService:RoomserviceService,
    private router: Router,
  @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) { }

  ngOnInit() {
    this.selectedBooking=null;
    this.aliments=this.data.aliments;
    this.calculateOrderPrice();
    this.getUserActiveBookings();
  }


  private getUserActiveBookings(): void {
    this.bookingService.getAllCustomerActiveBookings().subscribe(bookings => {
      this.bookings = bookings;
    });
  }

  private calculateOrderPrice():void{
    this.orderPrice=0;
    for(let aliment of this.aliments){
      this.orderPrice+=aliment.price;
    }
  }
  private order(): void{
    let roomserviceCreation=new RoomserviceCreation();
    roomserviceCreation.aliments=this.aliments;
    roomserviceCreation.bookingId=this.selectedBooking.id;
    this.roomserviceService.add(roomserviceCreation).subscribe(result=>{
      this.router.navigate(['/profile']);
    });
  }

}
