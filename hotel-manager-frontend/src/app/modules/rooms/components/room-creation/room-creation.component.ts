import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Room} from "../../../../core/models/room.model";
import {RoomService} from "../../../../core/services/room.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-room-creation',
  templateUrl: './room-creation.component.html',
  styleUrls: ['./room-creation.component.css']
})
export class RoomCreationComponent implements OnInit {

  roomCreationForm: FormGroup;
  uploadFile: any;
  private options = {headers: new HttpHeaders().set("Accept", "application/json" )};

  constructor(
    private formBuilder: FormBuilder,
    private roomService: RoomService,
    private httpClient: HttpClient
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
    this.roomService.add(roomToCreate).subscribe();
    let formData = new FormData();
    formData.append('image',this.uploadFile,this.uploadFile.name);
    // formData.append('roomDto',JSON.stringify(roomToCreate),'roomDto');
    this.httpClient
      .post("http://localhost:8082/rooms/111/image", formData, this.options).subscribe();
    this.roomCreationForm = this.createFormGroup();
  }

  onUpload(event) {
    for (let file of event.files) {
      this.uploadFile = file;
    }
  }
}
