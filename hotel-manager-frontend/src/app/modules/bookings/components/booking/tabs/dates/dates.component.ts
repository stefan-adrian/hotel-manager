import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ValidatorService} from "../../../../../../shared/services/validator.service";
import {BookingComponent} from "../../booking.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-dates',
  templateUrl: './dates.component.html',
  styleUrls: ['./dates.component.css']
})
export class DatesComponent implements OnInit {

  dates: Date[];

  rangeDates: Date[];

  minDate: Date;

  maxDate: Date;

  invalidDates: Array<Date>;

  bookingDatesForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router
  ){

  }

  ngOnInit() {
    let today = new Date();
    let month = today.getMonth();
    let year = today.getFullYear();
    let prevMonth = (month === 0) ? 11 : month -1;
    let prevYear = (prevMonth === 11) ? year - 1 : year;
    let nextMonth = (month === 11) ? 0 : month + 1;
    let nextYear = year+3;
    this.minDate = new Date();
    this.maxDate = new Date();
    this.maxDate.setFullYear(nextYear);

    let invalidDate = new Date();
    invalidDate.setDate(today.getDate() - 1);
    this.invalidDates = [today,invalidDate];

    this.bookingDatesForm=this.createFormGroup();
  }

  createFormGroup(): FormGroup{
    return this.formBuilder.group({
      arrival:[null,Validators.required],
      departure:[null,Validators.required]
      },
      {
        validator:
          ValidatorService.datesNotOverlap

      }
    );
  }

  save(){
    BookingComponent.arrival=this.bookingDatesForm.get('arrival').value;
    BookingComponent.departure=this.bookingDatesForm.get('departure').value;
    this.router.navigate(['/bookings', {outlets: {sub: ['rooms']}}]);

  }


}
