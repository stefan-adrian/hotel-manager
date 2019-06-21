import { Component, OnInit } from '@angular/core';
import {DataService} from "../../../../../../shared/services/data.service";
import {DateFormat} from "../../../../../../shared/pipes/date-format";
import {CategoryBooking} from "../../../../../../core/models/category-booking.model";
import {Router} from "@angular/router";
import {BookingCreation} from "../../../../../../core/models/booking-creation.model";
import {catchError, map} from "rxjs/operators";
import {of} from "rxjs";
import {BookingService} from "../../../../../../core/services/booking.service";
import {Message} from "primeng/api";
import {Payment} from "../../../../../../core/models/payment.model";
import {PaymentService} from "../../../../../../core/services/payment.service";
import {AuthenticationService} from "../../../../../../core/services/authentication.service";

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css'],
  providers: [DateFormat]
})
export class ConfirmationComponent implements OnInit {

  private arrival:Date;
  private departure: Date;
  private categoryBooking: CategoryBooking;
  private message: Message[] = [];
  private loading: boolean = false;
  private color = 'primary';
  private mode = 'indeterminate';
  private value = 50;


  constructor(
    private dataService: DataService,
    private router: Router,
    private dateFormat: DateFormat,
    private bookingService: BookingService,
    private authenticationService: AuthenticationService,
    private paymentService: PaymentService
  ) { }

  ngOnInit() {
    this.arrival=this.dateFormat.transform(this.dataService.getArrival());
    this.departure=this.dateFormat.transform(this.dataService.getDeparture());
    this.categoryBooking=this.dataService.getCategoryBooking();
    var tablinks;
    tablinks = document.getElementsByClassName("tablinks");
    tablinks[2].className+= " active";
  }

  private book(): void{
    let booking=new BookingCreation();
    booking.fromTime=this.arrival;
    booking.toTime=this.departure;
    booking.roomCategoryName=this.categoryBooking.name;
    booking.bookingPrice=this.categoryBooking.totalBookingPrice;
    booking.customerEmail=this.getUsername();
    if(this.authenticationService.getUserRole()==='ROLE_USER') {
      this.loading=true;
      localStorage.setItem('bookingDetails', JSON.stringify(booking));
      let payment = new Payment();
      payment.amount = this.categoryBooking.totalBookingPrice;
      payment.itemName = this.categoryBooking.name;
      console.log(payment);
      this.paymentService.createPayment(payment).subscribe(result => {
        let paymentLink = JSON.parse(JSON.stringify(result));
        window.location.href = paymentLink.link;

      });
    }

    if(this.authenticationService.getUserRole()==='ROLE_EMPLOYEE') {
      this.bookingService.add(booking).pipe(
        map(response=>{
          this.router.navigate(['/profile/bookings']);
        }),
        catchError((err) => {
          this.message = [];
          this.message.push({severity: 'error', summary: err.message});
          return of();
        }))
        .subscribe();
    }
  }


  private getUsername(): string {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    return currentUser.username;
  }
}
