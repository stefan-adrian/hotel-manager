import {Component, OnInit} from '@angular/core';
import {Aliment} from "../../../../core/models/aliment.model";
import {AlimentService} from "../../../../core/services/aliment.service";
import {SelectItem} from "primeng/api";


@Component({
  selector: 'app-aliment-list',
  templateUrl: './aliment-list.component.html',
  styleUrls: ['./aliment-list.component.css']
})
export class AlimentListComponent implements OnInit {

  private aliments: Aliment[];

  private sortOrder: number;

  private sortOptions: SelectItem[];

  private sortField: string;

  private sortKey: string;

  constructor(
    private alimentService: AlimentService
  ) {
  }

  ngOnInit() {
    this.getAliments();

    this.sortOptions = [
      {label: 'Ascending Price', value: 'price'},
      {label: 'Descending Price', value: '!price'}
    ];
  }

  private getAliments(): void {
    this.alimentService.getAll()
      .subscribe(aliments => {
        this.aliments = aliments;
      });
  }

  private onSortChange(event) {
    let value = event.value;

    if (value.indexOf('!') === 0) {
      this.sortOrder = -1;
      this.sortField = value.substring(1, value.length);
    }
    else {
      this.sortOrder = 1;
      this.sortField = value;
    }
  }
}
