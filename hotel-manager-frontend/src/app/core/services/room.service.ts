import {Injectable} from '@angular/core';
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {Room} from "../models/room.model";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private options = {headers: new HttpHeaders().set("Accept", "application/json" )};

  constructor(
    private apiService: ApiService,
    private httpClient: HttpClient
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

  addImage(room: Room,uploadFile: any): void{
    let formData = new FormData();
    formData.append('image',uploadFile,uploadFile.name);
    this.apiService.patch(`/rooms/${room.id}/image`,formData).subscribe();
    // this.httpClient
    //   .patch("http://localhost:8082/rooms/"+room.id+"/image", formData, this.options).subscribe();
  }
}
