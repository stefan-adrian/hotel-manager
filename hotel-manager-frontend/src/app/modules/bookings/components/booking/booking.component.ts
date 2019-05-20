import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {

  navLinks: any[];
  activeLinkIndex = -1;
  dates: any;
  rooms: any;
  public static arrival: Date;
  public static departure: Date;

  constructor(private router: Router) {
    this.navLinks = [
      {
        label: 'Select Dates',
        link: 'dates',
        index: 0
      }, {
        label: 'Select Rooms',
        link: 'rooms',
        index: 1
      }
    ];
    this.dates = {
      label: 'Select Dates',
      link: 'dates',
      active: false,
      index: 0
    };
    this.rooms={
      label: 'Select Rooms',
      link: 'rooms',
      active: false,
      index: 1
    };
  }

  ngOnInit() {
    /*this.router.events.subscribe((res) => {
      this.activeLinkIndex = this.navLinks.indexOf(this.navLinks.find(tab => tab.link === '.' + this.router.url));
    });*/
  }

}
