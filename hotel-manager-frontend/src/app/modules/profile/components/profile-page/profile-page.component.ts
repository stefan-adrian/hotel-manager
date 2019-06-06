import { Component, OnInit } from '@angular/core';
import {BookingService} from "../../../../core/services/booking.service";
import {Booking} from "../../../../core/models/booking.model";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {

  private booking: Booking;

  constructor(
    private bookingService: BookingService
  ) { }

  ngOnInit() {
    this.getUserNextBooking();
  }

  private getUserNextBooking():void{
    this.bookingService.getCustomerNextBooking().subscribe(booking=>{
      this.booking=booking;
    });
  }

}
