import { Component, OnInit } from '@angular/core';
import {RoomserviceCreation} from "../../../../core/models/roomservice-creation.model";
import {RoomserviceService} from "../../../../core/services/roomservice.service";
import {AuthenticationService} from "../../../../core/services/authentication.service";

@Component({
  selector: 'app-account-roomservices',
  templateUrl: './account-roomservices.component.html',
  styleUrls: ['./account-roomservices.component.css']
})
export class AccountRoomservicesComponent implements OnInit {

  private roomservices: RoomserviceCreation[];
  private panelOpenState = false;

  constructor(
    private roomserviceService:RoomserviceService,
    private authenticationService: AuthenticationService

  ) { }

  ngOnInit() {
    this.getUserRoomservices();
  }

  private getUserRoomservices():void{
    this.roomserviceService.getAllCustomerRoomservices().subscribe(roomservices=>{
      this.roomservices=roomservices;

    });
  }

}
