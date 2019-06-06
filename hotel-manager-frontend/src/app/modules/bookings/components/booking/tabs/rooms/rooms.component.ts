import {Component, OnInit} from '@angular/core';
import {DataService} from "../../../../../../shared/services/data.service";
import {DateFormat} from "../../../../../../shared/pipes/date-format";
import {CategoryBooking} from "../../../../../../core/models/category-booking.model";
import {Router} from "@angular/router";
import {CategoryService} from "../../../../../../core/services/category.service";

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css'],
  providers: [DateFormat]
})
export class RoomsComponent implements OnInit {

  private arrival:Date;
  private departure: Date;
  private categoryBookings: CategoryBooking[];

  constructor(
    private dataService: DataService,
    private categoryService: CategoryService,
    private router: Router,
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

  private getRoomsBetweenDates(): void {
    this.categoryService.getAllBetweenDates(this.arrival,this.departure)
      .subscribe(categoryBookings => this.categoryBookings = categoryBookings);
  }

  private book(categoryBooking: CategoryBooking): void{
    this.dataService.setCategoryBooking(categoryBooking);
    this.router.navigate(['/bookings', {outlets: {sub: ['confirmation']}}]);
  }
}
