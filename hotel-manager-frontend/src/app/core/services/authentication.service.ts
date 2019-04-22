import {Injectable} from '@angular/core';
import {ApiService} from "./api.service";
import {LoginModel} from "../models/login.model";
import {Observable, of, throwError} from "rxjs";
import {catchError, map} from "rxjs/operators";
import decode from 'jwt-decode';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private apiService: ApiService
  ) {
  }

  authenticate(loginModel: LoginModel): Observable<boolean> {
    return this.apiService.post('/authentication', loginModel).pipe(
      map((response: Response) => {
          let token = JSON.parse(JSON.stringify(response));
          if (token) {
            localStorage.setItem('currentUser', JSON.stringify({username: loginModel.username, token: token}));
            return true;
          } else {
            return false;
          }
        }
      )
    );

  }

  getToken(): String {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var token = currentUser && currentUser.token;
    return token ? token.token : "";
  }

  logout(): void {
    localStorage.removeItem('currentUser');
  }

  isLoggedIn(): boolean {
    var token: String = this.getToken();
    return token && token.length > 0;
  }

  getUserRole(): String {
    var token: String = this.getToken();
    if (token && token.length > 0) {
      const tokenPayload = decode(token);
      return tokenPayload.role;
    }
    return "";

  }
}
