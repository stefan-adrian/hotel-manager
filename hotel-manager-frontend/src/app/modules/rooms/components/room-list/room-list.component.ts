import {Component, OnInit} from '@angular/core';
import {RoomService} from "../../../../core/services/room.service";
import {Room} from "../../../../core/models/room.model";
import {SelectItem} from "primeng/api";

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {

  rooms: Room[];

  sortOrder: number;

  sortOptions: SelectItem[];

  sortField: string;

  sortKey: string;

  constructor(
    private roomService: RoomService
  ) {
  }

  ngOnInit() {
    this.getRooms();

    this.sortOptions = [
      {label: 'Ascending Price', value: 'price'},
      {label: 'Descending Price', value: '!price'}
    ];
  }

  getRooms(): void {
    this.roomService.getAll()
      .subscribe(rooms => this.rooms = rooms);
  }

  onSortChange(event) {
    let value = event.value;

    if (value.indexOf('!') === 0) {
      this.sortOrder = -1;
      this.sortField = value.substring(1, value.length);
    }
    else {
      this.sortOrder = 1;
      this.sortField = value;
    }
  }
}
