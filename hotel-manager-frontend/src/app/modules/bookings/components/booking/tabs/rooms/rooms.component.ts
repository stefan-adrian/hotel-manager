import { Component, OnInit } from '@angular/core';
import {BookingComponent} from "../../booking.component";

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css']
})
export class RoomsComponent implements OnInit {

  arrival:any;
  departure: any;

  constructor() { }

  ngOnInit() {
    var tablinks;
    this.arrival=BookingComponent.arrival;
    this.departure=BookingComponent.departure;
    tablinks = document.getElementsByClassName("tablinks");
    tablinks[1].className+= " active";
  }

}
