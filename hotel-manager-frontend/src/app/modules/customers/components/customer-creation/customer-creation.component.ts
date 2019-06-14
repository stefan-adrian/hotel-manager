import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Message} from "primeng/api";
import {CustomerService} from "../../../../core/services/customer.service";
import {Customer} from "../../../../core/models/customer.model";
import {AuthenticationService} from "../../../../core/services/authentication.service";
import {catchError, map} from "rxjs/operators";
import {of} from "rxjs";
import {Router} from "@angular/router";
import {DateFormat} from "../../../../shared/pipes/date-format";
import {CartDialogComponent} from "../../../aliments/components/cart-dialog/cart-dialog.component";
import {MatDialog} from "@angular/material";
import {TermsAndConditionsComponent} from "../terms-and-conditions/terms-and-conditions.component";

export interface Role {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-customer-creation',
  templateUrl: './customer-creation.component.html',
  styleUrls: ['./customer-creation.component.css'],
  providers: [DateFormat]
})
export class CustomerCreationComponent implements OnInit {

  private customerCreationForm: FormGroup;
  private message: Message[] = [];
  private hide = true;
  private roles: Role[] = [
    {value: 'ROLE_USER', viewValue: 'User'},
    {value: 'ROLE_ADMIN', viewValue: 'Admin'}
  ];
  private maxDate = new Date();

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private dateFormat: DateFormat,
    public dialog: MatDialog,
  private customerService: CustomerService,
    private authenticationService: AuthenticationService
  ) {
  }

  ngOnInit() {
    this.maxDate.setFullYear(this.maxDate.getFullYear()-18);
    this.customerCreationForm = this.createFormGroup();
    this.authenticationService.logout();
  }

  private createFormGroup(): FormGroup {
    return this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: [null, Validators.required],
      name: [null, Validators.required],
      surname: [null, Validators.required],
      nationality: [null, Validators.required],
      address: [null, Validators.required],
      identificationNumber: [null, Validators.required],
      birthday: [null, Validators.required],
      role: [null, Validators.required],
      terms: [false]
    });
  }

  private revertFormGroup() {
    this.customerCreationForm = this.createFormGroup();
  }

  private save() {
    const customerToCreate: Customer = Object.assign({},
      this.customerCreationForm.value);
    customerToCreate.birthday=this.dateFormat.transform(customerToCreate.birthday);
    this.customerService.add(customerToCreate).pipe(
      map(response=>{
        this.message = [];
        this.message.push({severity: 'info', summary: 'Account Created'});
        this.router.navigate(['/login']);
      }),
      catchError((err) => {
        this.message = [];
        this.customerCreationForm.controls['email'].setErrors({'incorrect': true});
        this.message.push({severity: 'error', summary: err.message});
        return of();
      }))
    .subscribe();
  }

  private openTermsAndConditions():void{
    if(this.customerCreationForm.get('terms').value==true) {
      const dialogRefrence = this.dialog.open(TermsAndConditionsComponent, {
        width: '400px'
      });
    }
  }
}
