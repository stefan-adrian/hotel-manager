import { Component, OnInit } from '@angular/core';
import {BookingService} from "../../../../core/services/booking.service";
import {Booking} from "../../../../core/models/booking.model";

@Component({
  selector: 'app-all-bookings',
  templateUrl: './all-bookings.component.html',
  styleUrls: ['./all-bookings.component.css']
})
export class AllBookingsComponent implements OnInit {

  private bookings: Booking[];

  constructor(
    private bookingService: BookingService
  ) { }

  ngOnInit() {
    this.getAllBookings()
  }

  getAllBookings():void{
    this.bookingService.getAll().subscribe(bookings=>{
      this.bookings=bookings;
    })

  }

}
