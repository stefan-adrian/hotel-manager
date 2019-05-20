import { Injectable } from '@angular/core';
import {AbstractControl, FormControl, Validators} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class ValidatorService extends Validators{

  public static datesNotOverlap(control: AbstractControl){
    const arrival:string=control.get('arrival').value;
    const departure:string=control.get('departure').value;
    if(arrival>=departure){
      return {invalid: true};
    }
    return null;
  }

}
