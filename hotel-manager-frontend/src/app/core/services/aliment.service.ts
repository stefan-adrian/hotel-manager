import { Injectable } from '@angular/core';
import {ApiService} from "./api.service";
import {Aliment} from "../models/aliment.model";
import {Observable} from "rxjs";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AlimentService {

  constructor(
    private router: Router,
    private apiService: ApiService
  ) { }

  getAll(): Observable<Aliment[]>{
    return this.apiService.get('/aliments');
  }

  add(aliment : Aliment) : Observable<Aliment>{
    return this.apiService.post('/aliments', aliment);
  }

  addImage(aliment : Aliment, uploadFile: any) : void{
    let formData = new FormData();
    formData.append('image', uploadFile, uploadFile.name);
    this.apiService.patch(`/aliments/${aliment.id}/image`,formData).subscribe(
      ()=>{
        this.router.navigate(['/aliments']);
      }
    );
  }
}
