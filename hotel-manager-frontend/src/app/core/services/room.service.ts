import {Injectable} from '@angular/core';
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {Room} from "../models/room.model";
import {Router} from "@angular/router";

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

  addImage(room: Room, uploadFile: any): void {
    let formData = new FormData();
    formData.append('image', uploadFile, uploadFile.name);
    this.apiService.patch(`/rooms/${room.id}/image`, formData).subscribe(
      () => {
        this.router.navigate(['/rooms']);
      });
  }
}
