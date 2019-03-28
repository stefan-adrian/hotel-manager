import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Room} from "../../../../core/models/room.model";
import {RoomService} from "../../../../core/services/room.service";
import {Message} from "primeng/api";

@Component({
  selector: 'app-room-creation',
  templateUrl: './room-creation.component.html',
  styleUrls: ['./room-creation.component.css']
})
export class RoomCreationComponent implements OnInit {

  roomCreationForm: FormGroup;
  uploadFile: any;
  imageForm: any;
  message: Message[] = [];

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

  revertFormGroup(){
    this.uploadFile=null;
    this.roomCreationForm = this.createFormGroup();
    this.imageForm.clear();
  }

  save() {
    const roomToCreate: Room = Object.assign({},
      this.roomCreationForm.value);
    this.roomService.add(roomToCreate).subscribe(result=>
    {
      let room=JSON.parse(JSON.stringify(result));
      this.roomService.addImage(room,this.uploadFile);
      this.revertFormGroup();
    });
  }

  onUpload(event,form) {
    for (let file of event.files) {
      this.uploadFile = file;
    }
    this.imageForm=form;
  }

  showSuccess() {
    this.message = [];
    this.message.push({severity:'info', summary:'Room Created'});
  }
}
