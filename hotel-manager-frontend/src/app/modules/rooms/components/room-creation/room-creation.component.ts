import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Room} from "../../../../core/models/room.model";
import {RoomService} from "../../../../core/services/room.service";

@Component({
  selector: 'app-room-creation',
  templateUrl: './room-creation.component.html',
  styleUrls: ['./room-creation.component.css']
})
export class RoomCreationComponent implements OnInit {

  roomCreationForm: FormGroup;
  uploadFile: any;

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
      tv: [false],
      price: [null, Validators.required]
    });

  }

  save() {
    const roomToCreate: Room = Object.assign({},
      this.roomCreationForm.value);
    this.roomService.add(roomToCreate).subscribe(result=>
    {
      let room=JSON.parse(JSON.stringify(result));
      this.roomService.addImage(room,this.uploadFile);
    });


    this.roomCreationForm = this.createFormGroup();
  }

  onUpload(event) {
    for (let file of event.files) {
      this.uploadFile = file;
    }
  }
}
