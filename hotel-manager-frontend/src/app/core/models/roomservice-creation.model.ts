import {Aliment} from "./aliment.model";

export class RoomserviceCreation{
  id: number;
  timeOfOrder: string;
  aliments: Aliment[];
  totalCommandPrice: number;
  bookingId: number;
}
