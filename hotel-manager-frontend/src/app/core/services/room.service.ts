import {Injectable} from '@angular/core';
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {Room} from "../models/room.model";
import {Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";
import {CategoryBooking} from "../models/category-booking.model";

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(
    private router: Router,
    private apiService: ApiService
  ) {
  }

  getAll(): Observable<Room[]> {
    return this.apiService.get('/rooms');
  }

  getById(id: number): Observable<Room> {
    return this.apiService.get(`/rooms/${id}`);
  }

  add(room: Room): Observable<Room> {
    return this.apiService.post('/rooms', room);
  }

  getAllBetweenDates(arrivalDate: any,departureDate: any) : Observable<CategoryBooking[]>{
    let params = new HttpParams().append("arrivalDate",arrivalDate).append("departureDate",departureDate);
    return this.apiService.getWithParams('/rooms/available',params);
  }
}
