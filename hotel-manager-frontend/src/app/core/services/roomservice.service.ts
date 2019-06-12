import { Injectable } from '@angular/core';
import {ApiService} from "./api.service";
import {RoomserviceCreation} from "../models/roomservice-creation.model";
import {Observable} from "rxjs";

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
}
