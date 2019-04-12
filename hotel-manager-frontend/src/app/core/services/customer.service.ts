import {Injectable} from '@angular/core';
import {ApiService} from "./api.service";
import {Customer} from "../models/customer.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(
    private apiService: ApiService
  ) {
  }

  add(customer: Customer): Observable<Customer> {
    return this.apiService.post('/customers', customer);
  }
}
