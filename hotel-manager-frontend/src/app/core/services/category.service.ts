import {Injectable} from '@angular/core';
import {Router} from "@angular/router";
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {Room} from "../models/room.model";
import {Category} from "../models/category.model";

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
}
