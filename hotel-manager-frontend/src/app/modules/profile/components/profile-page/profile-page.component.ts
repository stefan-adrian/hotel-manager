import { Component, OnInit } from '@angular/core';
import {BookingService} from "../../../../core/services/booking.service";
import {Booking} from "../../../../core/models/booking.model";
import {CustomerService} from "../../../../core/services/customer.service";
import {Customer} from "../../../../core/models/customer.model";
import {saveAs} from "file-saver";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {

  private booking: Booking;
  private customer: Customer;

  constructor(
    private bookingService: BookingService,
    private customerService: CustomerService
  ) { }

  ngOnInit() {
    this.getUser();
    this.getUserNextBooking();
  }

  private getUserNextBooking():void{
    this.bookingService.getCustomerNextBooking().subscribe(booking=>{
      this.booking=booking;
    });
  }

  private getUser():void{
    this.customerService.getByEmail(this.getUsername()).subscribe(customer=>this.customer=customer);
  }

  private getUsername(): string {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    return currentUser.username;
  }

  private getBarcode():void{
    saveAs(this.base64toBlob(this.customer.qrCode),"qr.jpg");

  }

  private  base64toBlob(base64Data) {
    var sliceSize = 1024;
    var byteCharacters = atob(base64Data);
    var bytesLength = byteCharacters.length;
    var slicesCount = Math.ceil(bytesLength / sliceSize);
    var byteArrays = new Array(slicesCount);

    for (var sliceIndex = 0; sliceIndex < slicesCount; ++sliceIndex) {
      var begin = sliceIndex * sliceSize;
      var end = Math.min(begin + sliceSize, bytesLength);

      var bytes = new Array(end - begin);
      for (var offset = begin, i = 0; offset < end; ++i, ++offset) {
        bytes[i] = byteCharacters[offset].charCodeAt(0);
      }
      byteArrays[sliceIndex] = new Uint8Array(bytes);
    }
    return new Blob(byteArrays, { type: 'image/jpeg' });
  }
}
