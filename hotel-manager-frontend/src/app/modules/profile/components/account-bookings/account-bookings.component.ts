import {Component, OnInit} from '@angular/core';
import {Booking} from "../../../../core/models/booking.model";
import {BookingService} from "../../../../core/services/booking.service";
import {ActivatedRoute} from "@angular/router";
import {catchError, map} from "rxjs/operators";
import {of} from "rxjs";
import {Message} from "primeng/api";
import {BookingCreation} from "../../../../core/models/booking-creation.model";
import {AuthenticationService} from "../../../../core/services/authentication.service";

@Component({
  selector: 'app-account-bookings',
  templateUrl: './account-bookings.component.html',
  styleUrls: ['./account-bookings.component.css']
})
export class AccountBookingsComponent implements OnInit {

  private bookings: Booking[];
  private message: Message[] = [];
  private loading: boolean = true;
  private color = 'primary';
  private mode = 'indeterminate';
  private value = 50;

  constructor(
    private bookingService: BookingService,
    private route: ActivatedRoute,
    private authenticationService: AuthenticationService

  ) {
  }

  ngOnInit() {
    this.verifyIfPaymentAndBookingNeeded();
  }

  getUserBookings(): void {
    this.bookingService.getAllCustomerBookings().subscribe(bookings => {
      this.loading=false;
      this.bookings = bookings;
    });
  };

  verifyIfPaymentAndBookingNeeded(){
    let paymentId;
    let payerId;
    this.route.queryParams.subscribe(
      params => {
        paymentId = params['paymentId']
        payerId = params['PayerID'];
      });
    if (payerId != null) {
      let booking: BookingCreation;
      booking=this.getBookingFromStorage();
      booking.payerId=payerId;
      booking.paymentId=paymentId;

      this.bookingService.add(booking).pipe(
        map(response=>{
          this.getUserBookings();
        }),
        catchError((err) => {
          this.message = [];
          this.message.push({severity: 'error', summary: err.message});
          return of();
        }))
        .subscribe();
    } else{
      this.getUserBookings();
    }
  }

  private getBookingFromStorage(){
    return JSON.parse(localStorage.getItem('bookingDetails'));
  }

}
