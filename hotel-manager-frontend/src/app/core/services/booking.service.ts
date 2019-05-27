import { Injectable } from '@angular/core';
import {ApiService} from "./api.service";
import {BookingCreation} from "../models/booking-creation.model";
import {Observable} from "rxjs";

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
}
