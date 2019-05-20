import {Component, OnInit} from '@angular/core';
import {DataService} from "../../../../../../shared/services/data.service";

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css']
})
export class RoomsComponent implements OnInit {

  private arrival:any;
  private departure: any;

  constructor(private dataService: DataService) { }

  ngOnInit() {
    this.arrival=this.dataService.getArrival();
    this.departure=this.dataService.getDeparture();
    var tablinks;
    tablinks = document.getElementsByClassName("tablinks");
    tablinks[1].className+= " active";
  }

}
