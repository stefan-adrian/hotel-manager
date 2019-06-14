import {Injectable} from '@angular/core';
import {ApiService} from "./api.service";
import {RoomserviceCreation} from "../models/roomservice-creation.model";
import {Observable} from "rxjs";
import {HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RoomserviceService {

  constructor(
    private apiService: ApiService
  ) { }

  add(roomservice: RoomserviceCreation): Observable<any>{
    return this.apiService.post('/bookings/'+roomservice.bookingId+'/roomservices',roomservice);
  }

  getAllCustomerRoomservices(): Observable<RoomserviceCreation[]>{
    let params = new HttpParams().append("email",this.getUsername());
    return this.apiService.getWithParams('/room-services',params);
  }

  getUsername(): string {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    return currentUser.username;
  }
}
