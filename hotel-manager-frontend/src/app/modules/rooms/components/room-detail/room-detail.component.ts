import { Component, OnInit } from '@angular/core';
import {Room} from "../../../../core/models/room.model";
import {ActivatedRoute} from "@angular/router";
import {RoomService} from "../../../../core/services/room.service";

@Component({
  selector: 'app-room-detail',
  templateUrl: './room-detail.component.html',
  styleUrls: ['./room-detail.component.css']
})
export class RoomDetailComponent implements OnInit {

  room: Room;

  constructor(
    private route:ActivatedRoute,
    private roomService: RoomService
  ) { }

  ngOnInit() {
    this.getRoom();
  }

  getRoom(): void{
    const id = +this.route.snapshot.paramMap.get('id');
    this.roomService.getById(id)
      .subscribe(room=>this.room=room);
  }
}
