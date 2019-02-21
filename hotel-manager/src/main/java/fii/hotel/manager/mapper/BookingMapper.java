package fii.hotel.manager.mapper;

import fii.hotel.manager.dto.BookingCreationDto;
import fii.hotel.manager.model.Booking;
import fii.hotel.manager.model.Customer;
import fii.hotel.manager.model.Room;
import org.springframework.stereotype.Service;

@Service
public class BookingMapper {

    public Booking map(Customer customer, Room room, BookingCreationDto bookingCreationDto) {
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setRoom(room);
        booking.setRoomCleaning(bookingCreationDto.getRoomCleaning());
        booking.setFromTime(bookingCreationDto.getFromTime());
        booking.setToTime(bookingCreationDto.getToTime());
        return booking;
    }

    public BookingCreationDto map(Booking booking) {
        BookingCreationDto bookingCreationDto = new BookingCreationDto();
        bookingCreationDto.setId(booking.getId());
        bookingCreationDto.setRoomId(booking.getRoom().getId());
        bookingCreationDto.setCustomerId(booking.getCustomer().getId());
        bookingCreationDto.setFromTime(booking.getFromTime());
        bookingCreationDto.setToTime(booking.getToTime());
        bookingCreationDto.setRoomCleaning(booking.getRoomCleaning());
        return bookingCreationDto;
    }
}
