import { Component, OnInit } from '@angular/core';
import {Booking} from "../../../../core/models/booking.model";
import {BookingService} from "../../../../core/services/booking.service";

@Component({
  selector: 'app-account-bookings',
  templateUrl: './account-bookings.component.html',
  styleUrls: ['./account-bookings.component.css']
})
export class AccountBookingsComponent implements OnInit {

  private bookings: Booking[];

  constructor(
    private bookingService: BookingService
  ) { }

  ngOnInit() {
    this.getUserBookings();
  }

  getUserBookings():void{
    this.bookingService.getAllCustomerBookings().subscribe(bookings=>{
      this.bookings=bookings;
    });
  }

}
