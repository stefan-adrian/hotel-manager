export class BookingCreation{
  id: number;
  roomId: number;
  customerEmail: string;
  roomCategoryName: string;
  fromTime: Date;
  toTime: Date;
  bookingPrice: number;
  roomCleaning: boolean;
  payerId: string;
  paymentId: string;
}
