import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CategoryService} from "../../../../core/services/category.service";
import {Category} from "../../../../core/models/category.model";

@Component({
  selector: 'app-category-creation',
  templateUrl: './category-creation.component.html',
  styleUrls: ['./category-creation.component.css']
})
export class CategoryCreationComponent implements OnInit {

  private categoryCreationForm: FormGroup;
  private uploadFile: any;
  private imageForm: any;

  constructor(
    private formBuilder: FormBuilder,
    private categoryService: CategoryService
  ) { }

  ngOnInit() {
    this.categoryCreationForm = this.createFormGroup();
  }

  private createFormGroup(): FormGroup {
    return this.formBuilder.group({
      name: [null, Validators.required],
      beds: [null, Validators.required],
      tv: [false],
      price: [null, Validators.required],
      size: [null, Validators.required],
      description: [null, Validators.required]
    });

  }

  private save() {
    const categoryToCreate: Category = Object.assign({},
      this.categoryCreationForm.value);
    this.categoryService.add(categoryToCreate).subscribe(result=>
    {
      let category=JSON.parse(JSON.stringify(result));
      this.categoryService.addImage(category,this.uploadFile);
    });
  }

  private onUpload(event,form) {
    for (let file of event.files) {
      this.uploadFile = file;
    }
    this.imageForm=form;
  }

}
