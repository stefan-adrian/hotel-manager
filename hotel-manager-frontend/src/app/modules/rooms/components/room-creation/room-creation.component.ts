import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Room} from "../../../../core/models/room.model";
import {RoomService} from "../../../../core/services/room.service";
import {Message} from "primeng/api";
import {Router} from "@angular/router";
import {CategoryService} from "../../../../core/services/category.service";
import {Category} from "../../../../core/models/category.model";

@Component({
  selector: 'app-room-creation',
  templateUrl: './room-creation.component.html',
  styleUrls: ['./room-creation.component.css']
})
export class RoomCreationComponent implements OnInit {

  roomCreationForm: FormGroup;
  message: Message[] = [];
  categories: Category[];

  constructor(
    private formBuilder: FormBuilder,
    private roomService: RoomService,
    private categoryService: CategoryService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.getCategories();
    this.roomCreationForm = this.createFormGroup();
  }

  getCategories(): void{
    this.categoryService.getAll()
      .subscribe(categories=>this.categories=categories);
  }

  createFormGroup(): FormGroup {
    return this.formBuilder.group({
      name: [null, Validators.required],
      floor: [null, Validators.required],
      category: [null, Validators.required]
    });

  }

  save() {
    const roomToCreate: Room = Object.assign({},
      this.roomCreationForm.value);
    this.roomService.add(roomToCreate).subscribe(result=>
    {
      this.showSuccess();
      this.router.navigate(['/rooms']);
    });
  }

  showSuccess() {
    this.message = [];
    this.message.push({severity:'info', summary:'Room Created'});
  }
}
