import {Component, OnInit} from '@angular/core';
import {RoomService} from "../../../../core/services/room.service";
import {Room} from "../../../../core/models/room.model";

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {

  rooms: Room[];

  constructor(
    private roomService: RoomService
  ) {
  }

  ngOnInit() {
    this.getRooms();
  }

  getRooms(): void {
    this.roomService.getAll()
      .subscribe(rooms => this.rooms = rooms);
  }
}
