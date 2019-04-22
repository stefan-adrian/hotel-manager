import {Injectable} from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";

const BASE_URL = environment.api_url;

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private options = {headers: new HttpHeaders().set('Content-Type', 'application/json')
      .set('Authorization', 'Bearer ' + this.getToken())};
  private optionsFile = {headers: new HttpHeaders().set("Accept", "application/json")
      .set('Authorization', 'Bearer ' + this.getToken())};


  constructor(private httpClient: HttpClient) {
  }

  getToken(): String {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var token = currentUser && currentUser.token;
    return token ? token.token : "";
  }

  private formatErrors(error: any) {
    return throwError(error.error);
  }

  public get(path: string, params: HttpParams = new HttpParams()): Observable<any> {
    return this.httpClient.get(BASE_URL + path, this.options).pipe(catchError(this.formatErrors));
  }

  public put(path: string, body: object = {}): Observable<any> {
    return this.httpClient
      .put(BASE_URL + path, JSON.stringify(body), this.options)
      .pipe(catchError(this.formatErrors));
  }

  public post(path: string, body: object = {}): Observable<any> {
    return this.httpClient
      .post(BASE_URL + path, JSON.stringify(body), this.options)
      .pipe(catchError(this.formatErrors));
  }

  public delete(path: string): Observable<any> {
    return this.httpClient.delete(BASE_URL + path).pipe(catchError(this.formatErrors));
  }

  public patch(path: string, form: FormData): Observable<any> {
    return this.httpClient.patch(BASE_URL + path, form, this.optionsFile);
  }

}
