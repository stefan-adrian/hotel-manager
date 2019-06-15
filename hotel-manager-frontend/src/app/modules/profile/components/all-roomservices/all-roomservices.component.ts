import {Component, OnInit} from '@angular/core';
import {RoomserviceService} from "../../../../core/services/roomservice.service";
import {AuthenticationService} from "../../../../core/services/authentication.service";

@Component({
  selector: 'app-all-roomservices',
  templateUrl: './all-roomservices.component.html',
  styleUrls: ['./all-roomservices.component.css']
})
export class AllRoomservicesComponent implements OnInit {

  private allRoomService: any;

  constructor(
    private roomserviceService: RoomserviceService,
    private authenticationService: AuthenticationService

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
    this.roomserviceService.takeOrderToNextStep(roomServiceId).subscribe(result=>this.getAllRoomservices());
  }
}
