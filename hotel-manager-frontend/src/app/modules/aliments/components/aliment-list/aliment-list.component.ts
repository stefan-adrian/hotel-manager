import {Component, OnInit} from '@angular/core';
import {Aliment} from "../../../../core/models/aliment.model";
import {AlimentService} from "../../../../core/services/aliment.service";


@Component({
  selector: 'app-aliment-list',
  templateUrl: './aliment-list.component.html',
  styleUrls: ['./aliment-list.component.css']
})
export class AlimentListComponent implements OnInit {

  aliments: Aliment[];

  constructor(
    private alimentService: AlimentService
  ) {
  }

  ngOnInit() {
    this.getAliments();
  }

  getAliments(): void {
    this.alimentService.getAll()
      .subscribe(aliments => {
        this.aliments = aliments;
      });
  }
}
