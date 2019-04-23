import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Message} from "primeng/api";
import {CustomerService} from "../../../../core/services/customer.service";
import {Customer} from "../../../../core/models/customer.model";
import {AuthenticationService} from "../../../../core/services/authentication.service";

export interface Role {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-customer-creation',
  templateUrl: './customer-creation.component.html',
  styleUrls: ['./customer-creation.component.css']
})
export class CustomerCreationComponent implements OnInit {

  customerCreationForm: FormGroup;
  message: Message[] = [];
  hide = true;
  roles: Role[] = [
    {value: 'ROLE_USER', viewValue: 'User'},
    {value: 'ROLE_ADMIN', viewValue: 'Admin'}
  ];

  constructor(
    private formBuilder: FormBuilder,
    private customerService: CustomerService,
    private authenticationService: AuthenticationService
  ) {
  }

  ngOnInit() {
    this.customerCreationForm = this.createFormGroup();
    this.authenticationService.logout();
  }

  createFormGroup(): FormGroup {
    return this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: [null, Validators.required],
      name: [null, Validators.required],
      surname: [null, Validators.required],
      role: [null, Validators.required]
    });
  }

  revertFormGroup() {
    this.customerCreationForm = this.createFormGroup();
  }

  save() {
    const customerToCreate: Customer = Object.assign({},
      this.customerCreationForm.value);
    this.customerService.add(customerToCreate).subscribe();
    this.revertFormGroup();
  }

  showSuccess() {
    this.message = [];
    this.message.push({severity: 'info', summary: 'Account Created'});
  }

}
