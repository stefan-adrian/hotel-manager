import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ValidatorService} from "../../../../../../shared/services/validator.service";
import {Router} from "@angular/router";
import {DataService} from "../../../../../../shared/services/data.service";

@Component({
  selector: 'app-dates',
  templateUrl: './dates.component.html',
  styleUrls: ['./dates.component.css']
})
export class DatesComponent implements OnInit {

  private dates: Date[];

  private minDate: Date;

  private bookingDatesForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private dataService: DataService
  ){

  }

  ngOnInit() {
    this.minDate = new Date();
    this.bookingDatesForm=this.createFormGroup();
  }

  private createFormGroup(): FormGroup{
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

  private save(){
    this.dataService.setArrivalDate(this.bookingDatesForm.get('arrival').value);
    this.dataService.setDepartureDate(this.bookingDatesForm.get('departure').value);
    this.router.navigate(['/bookings', {outlets: {sub: ['rooms']}}]);

  }


}
