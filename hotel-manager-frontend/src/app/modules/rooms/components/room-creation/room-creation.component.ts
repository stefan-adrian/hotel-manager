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
  tv: boolean;

  constructor(
    private formBuilder: FormBuilder,
    private roomService: RoomService
  ) {
  }

  ngOnInit() {
    this.roomCreationForm = this.createFormGroup();
    this.tv = false;
  }

  createFormGroup(): FormGroup {
    return this.formBuilder.group({
      name: [null, Validators.required],
      floor: [null, Validators.required],
      beds: [null, Validators.required],
      price: [null, Validators.required]
    });

  }

  save() {
    console.log(this.tv);
    const roomToCreate: Room = Object.assign({},
      this.roomCreationForm.value);
    roomToCreate.tv = this.tv;
    this.roomService.add(roomToCreate).subscribe();
    this.roomCreationForm = this.createFormGroup();
  }
}
