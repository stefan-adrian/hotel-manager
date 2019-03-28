import { Injectable } from '@angular/core';
import {ApiService} from "./api.service";
import {Aliment} from "../models/aliment.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AlimentService {

  constructor(
    private apiService: ApiService
  ) { }

  add(aliment : Aliment) : Observable<Aliment>{
    console.log("Aici2");
    return this.apiService.post('/aliments', aliment);
  }

  addImage(aliment : Aliment, uploadFile: any) : void{
    console.log("AICI3");
    let formData = new FormData();
    formData.append('image', uploadFile, uploadFile.name);
    this.apiService.patch(`/aliments/${aliment.id}/image`,formData).subscribe();
  }
}
