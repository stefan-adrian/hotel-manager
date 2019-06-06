import {Room} from "./room.model";

export class Booking{

  id: number;
  fromTime: string;
  toTime: string;
  roomCleaning: boolean;
  price: number;
  bookingTime:string;
  room: Room;
}
