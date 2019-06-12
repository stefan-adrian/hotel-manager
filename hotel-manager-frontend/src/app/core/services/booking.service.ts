import { Injectable } from '@angular/core';
import {ApiService} from "./api.service";
import {BookingCreation} from "../models/booking-creation.model";
import {Observable} from "rxjs";
import {Booking} from "../models/booking.model";
import {HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  constructor(
    private apiService: ApiService
  ) { }

  add(booking: BookingCreation): Observable<any>{
    return this.apiService.post('/bookings', booking);
  }

  getAllCustomerBookings(): Observable<Booking[]>{
    let params = new HttpParams().append("email",this.getUsername());
    return this.apiService.getWithParams('/bookings/customer',params);
  }

  getAllCustomerActiveBookings(): Observable<Booking[]>{
    let params = new HttpParams().append("email",this.getUsername());
    return this.apiService.getWithParams('/bookings/customer-active',params);
  }

  getCustomerNextBooking(): Observable<Booking>{
    let params = new HttpParams().append("email",this.getUsername());
    return this.apiService.getWithParams('/bookings/customer-next',params);
  }

  getUsername(): string {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    return currentUser.username;
  }
}
