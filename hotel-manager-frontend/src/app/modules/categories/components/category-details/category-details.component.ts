import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CategoryService} from "../../../../core/services/category.service";
import {Category} from "../../../../core/models/category.model";

@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.css']
})
export class CategoryDetailsComponent implements OnInit {

  private category: Category;

  constructor(
    private route:ActivatedRoute,
    private categoryService: CategoryService
  ) { }

  ngOnInit() {
    this.getCategory();
  }

  private getCategory():void{
    const id = +this.route.snapshot.paramMap.get('id');
    this.categoryService.getById(id)
      .subscribe(category=>this.category=category);
  }
}
