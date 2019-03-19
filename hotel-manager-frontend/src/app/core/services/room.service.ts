import { Injectable } from '@angular/core';
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {Room} from "../models/room.model";

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(
    private apiService: ApiService
  ) { }

  getAll(): Observable<Room[]>{
    return this.apiService.get('/rooms');
  }

  getById(id: number):Observable<Room>{
    return this.apiService.get(`/rooms/${id}`);
  }
}
