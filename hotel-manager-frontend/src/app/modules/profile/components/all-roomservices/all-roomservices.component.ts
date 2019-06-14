import { Component, OnInit } from '@angular/core';
import {AllRoomservice} from "../../../../core/models/all-roomservice.model";
import {RoomserviceService} from "../../../../core/services/roomservice.service";
import {RoomserviceCreation} from "../../../../core/models/roomservice-creation.model";

@Component({
  selector: 'app-all-roomservices',
  templateUrl: './all-roomservices.component.html',
  styleUrls: ['./all-roomservices.component.css']
})
export class AllRoomservicesComponent implements OnInit {

  private allRoomService: any;

  constructor(
    private roomserviceService: RoomserviceService
  ) { }

  ngOnInit() {
    this.getAllRoomservices();
  }

  private getAllRoomservices():void{
    this.roomserviceService.getAll().subscribe(allRoomServices=>{
      this.allRoomService=allRoomServices;
    });
  }

  private nextOrderStep(roomServiceId: number):void{
    console.log("Merge");
  }
}
