import {Injectable} from '@angular/core';
import {Router} from "@angular/router";
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {Category} from "../models/category.model";
import {CategoryBooking} from "../models/category-booking.model";
import {HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(
    private router: Router,
    private apiService: ApiService
  ) { }

  getAll(): Observable<Category[]> {
    return this.apiService.get('/categories');
  }

  add(category: Category): Observable<Category> {
    return this.apiService.post('/categories', category);
  }

  addImage(category: Category, uploadFile: any): void {
    let formData = new FormData();
    formData.append('image', uploadFile, uploadFile.name);
    this.apiService.patch(`/categories/${category.id}/image`, formData).subscribe(
      () => {
        this.router.navigate(['/categories']);
      });
  }

  getAllBetweenDates(arrivalDate: any,departureDate: any) : Observable<CategoryBooking[]>{
    let params = new HttpParams().append("arrivalDate",arrivalDate).append("departureDate",departureDate).append("email",this.getUsername());
    return this.apiService.getWithParams('/categories/available',params);
  }

  getUsername(): string {
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    return currentUser.username;
  }

  getById(id: number): Observable<Category>{
    return this.apiService.get(`/categories/${id}`);
  }
}
