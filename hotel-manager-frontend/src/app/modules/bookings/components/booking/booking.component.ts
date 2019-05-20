import {Component, OnDestroy, OnInit} from '@angular/core';
import {DataService} from "../../../../shared/services/data.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit, OnDestroy {

  private arrival: Date = null;
  private arrivalSubscription: Subscription;
  private departure: Date = null;
  private departureSubscription: Subscription;

  constructor(private dataService: DataService) {

  }

  ngOnInit() {
    this.arrivalSubscription = this.dataService.getArrivalObservable()
      .subscribe((arrival: Date) => this.arrival = arrival);
    this.departureSubscription = this.dataService.getDepartureObservable()
      .subscribe((departure: Date) => this.departure= departure);

  }

  ngOnDestroy() {
    this.arrivalSubscription.unsubscribe();
    this.departureSubscription.unsubscribe();
  }

}
