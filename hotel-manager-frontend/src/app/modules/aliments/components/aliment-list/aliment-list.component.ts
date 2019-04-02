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

  aliments: Aliment[];

  sortOrder: number;

  sortOptions: SelectItem[];

  sortField: string;

  sortKey: string;

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

  getAliments(): void {
    this.alimentService.getAll()
      .subscribe(aliments => {
        this.aliments = aliments;
      });
  }

  onSortChange(event) {
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
