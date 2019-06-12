import { Injectable } from '@angular/core';
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {Payment} from "../models/payment.model";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(
    private apiService: ApiService
  ) { }

  createPayment(payment: Payment): Observable<string>{
    return this.apiService.post('/payments',payment);
  }
}
