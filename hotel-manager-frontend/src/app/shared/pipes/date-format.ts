import {Injectable, Pipe, PipeTransform} from '@angular/core';
import {DatePipe} from "@angular/common";

@Injectable()
export class DateFormat extends DatePipe implements PipeTransform {
  transform(value: any): any {
    return super.transform(value, "dd/MM/yyyy");
  }
}
