import { Component, OnInit } from '@angular/core';
import {DataService} from "../../../../../../shared/services/data.service";
import {DateFormat} from "../../../../../../shared/pipes/date-format";
import {CategoryBooking} from "../../../../../../core/models/category-booking.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css'],
  providers: [DateFormat]
})
export class ConfirmationComponent implements OnInit {

  private arrival:Date;
  private departure: Date;
  private categoryBooking: CategoryBooking;

  constructor(
    private dataService: DataService,
    private router: Router,
    private dateFormat: DateFormat
  ) { }

  ngOnInit() {
    this.arrival=this.dateFormat.transform(this.dataService.getArrival());
    this.departure=this.dateFormat.transform(this.dataService.getDeparture());
    this.categoryBooking=this.dataService.getCategoryBooking();
    var tablinks;
    tablinks = document.getElementsByClassName("tablinks");
    tablinks[2].className+= " active";
  }

  book(): void{
    this.router.navigate(['/home']);
  }
}
