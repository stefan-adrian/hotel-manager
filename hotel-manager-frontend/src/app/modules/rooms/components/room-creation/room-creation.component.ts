import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Room} from "../../../../core/models/room.model";
import {RoomService} from "../../../../core/services/room.service";

@Component({
  selector: 'app-room-creation',
  templateUrl: './room-creation.component.html',
  styleUrls: ['./room-creation.component.css']
})
export class RoomCreationComponent implements OnInit {

  roomCreationForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private roomService: RoomService
  ) {
  }

  ngOnInit() {
    this.roomCreationForm = this.createFormGroup();
  }

  createFormGroup(): FormGroup {
    return this.formBuilder.group({
      name: [null, Validators.required],
      floor: [null, Validators.required],
      beds: [null, Validators.required],
      tv: [true],
      price: [null, Validators.required]
    });

  }

  save() {
    const roomToCreate: Room = Object.assign({},
      this.roomCreationForm.value);
    this.roomService.add(roomToCreate).subscribe();
    this.roomCreationForm=this.createFormGroup();
  }
}
