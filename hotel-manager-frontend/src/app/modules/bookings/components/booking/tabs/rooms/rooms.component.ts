import {Component, OnInit} from '@angular/core';
import {DataService} from "../../../../../../shared/services/data.service";
import {RoomService} from "../../../../../../core/services/room.service";
import {Room} from "../../../../../../core/models/room.model";
import {DateFormat} from "../../../../../../shared/pipes/date-format";

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css'],
  providers: [DateFormat]
})
export class RoomsComponent implements OnInit {

  private arrival:Date;
  private departure: Date;
  rooms: Room[];

  constructor(
    private dataService: DataService,
    private roomService: RoomService,
    private dateFormat: DateFormat
  ) { }

  ngOnInit() {
    this.arrival=this.dateFormat.transform(this.dataService.getArrival());
    this.departure=this.dateFormat.transform(this.dataService.getDeparture());
    var tablinks;
    tablinks = document.getElementsByClassName("tablinks");
    tablinks[1].className+= " active";
    this.getRoomsBetweenDates();
  }

  getRoomsBetweenDates(): void {
    this.roomService.getAllBetweenDates(this.arrival,this.departure)
      .subscribe(rooms => this.rooms = rooms);
  }

}
