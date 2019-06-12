import {Injectable} from '@angular/core';
import {ApiService} from "./api.service";
import {Customer} from "../models/customer.model";
import {Observable} from "rxjs";
import {HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(
    private apiService: ApiService
  ) {
  }

  add(customer: Customer): Observable<any> {
    return this.apiService.post('/customers', customer);
  }

  getByEmail(email: string): Observable<Customer>{
    let params = new HttpParams().append("email",email);
    return this.apiService.getWithParams('/customers/email',params);
  }

}
