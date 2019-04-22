import {Injectable} from '@angular/core';
import {ApiService} from "./api.service";
import {LoginModel} from "../models/login.model";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private apiService: ApiService
  ) { }

  authenticate(loginModel: LoginModel):boolean{
    console.log(loginModel);
    this.apiService.post('/authentication',loginModel).subscribe(result=>
    {
      let token=JSON.parse(JSON.stringify(result));
      console.log(token);
      return true;
    });
    return false;
  }
}
