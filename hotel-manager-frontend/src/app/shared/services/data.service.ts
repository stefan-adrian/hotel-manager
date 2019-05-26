import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {CategoryBooking} from "../../core/models/category-booking.model";

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private arrivalDate: BehaviorSubject<Date> = new BehaviorSubject<Date>(null);
  private arrival: Date;
  private departureDate: BehaviorSubject<Date> = new BehaviorSubject<Date>(null);
  private departure: Date;
  private categoryBookingSubject: BehaviorSubject<CategoryBooking> = new BehaviorSubject<CategoryBooking>(null);
  private categoryBooking: CategoryBooking;

  constructor() { }

  setArrivalDate(newArrivalDate: Date): void {
    this.arrival=newArrivalDate;
    this.arrivalDate.next(newArrivalDate);
  }

  getArrivalObservable(): Observable<Date> {
    return this.arrivalDate.asObservable();
  }

  getArrival(): Date{
    return this.arrival;
  }

  setDepartureDate(newDepartureDate: Date): void {
    this.departure=newDepartureDate;
    this.departureDate.next(newDepartureDate);
  }

  getDepartureObservable(): Observable<Date> {
    return this.departureDate.asObservable();
  }

  getDeparture(): Date{
    return this.departure;
  }

  setCategoryBooking(newCategoryBooking: CategoryBooking): void {
    this.categoryBooking=newCategoryBooking;
    this.categoryBookingSubject.next(newCategoryBooking);
  }

  getCategoryBookingObservable(): Observable<CategoryBooking> {
    return this.categoryBookingSubject.asObservable();
  }

  getCategoryBooking(): CategoryBooking{
    return this.categoryBooking;
  }
}
