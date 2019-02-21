package fii.hotel.manager.mapper;

import fii.hotel.manager.dto.BookingCreationDto;
import fii.hotel.manager.dto.BookingDto;
import fii.hotel.manager.model.Booking;
import fii.hotel.manager.model.Customer;
import fii.hotel.manager.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingMapper {

    private CustomerMapper customerMapper;
    private RoomMapper roomMapper;

    @Autowired
    public BookingMapper(CustomerMapper customerMapper, RoomMapper roomMapper) {
        this.customerMapper = customerMapper;
        this.roomMapper = roomMapper;
    }

    public Booking map(Customer customer, Room room, BookingCreationDto bookingCreationDto) {
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setRoom(room);
        booking.setRoomCleaning(bookingCreationDto.getRoomCleaning());
        booking.setFromTime(bookingCreationDto.getFromTime());
        booking.setToTime(bookingCreationDto.getToTime());
        return booking;
    }

    public BookingCreationDto mapToCreationDto(Booking booking) {
        BookingCreationDto bookingCreationDto = new BookingCreationDto();
        bookingCreationDto.setId(booking.getId());
        bookingCreationDto.setRoomId(booking.getRoom().getId());
        bookingCreationDto.setCustomerId(booking.getCustomer().getId());
        bookingCreationDto.setFromTime(booking.getFromTime());
        bookingCreationDto.setToTime(booking.getToTime());
        bookingCreationDto.setRoomCleaning(booking.getRoomCleaning());
        return bookingCreationDto;
    }

    public BookingDto map(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setRoomCleaning(booking.getRoomCleaning());
        bookingDto.setFromTime(booking.getFromTime());
        bookingDto.setToTime(booking.getToTime());
        bookingDto.setCustomerDto(customerMapper.map(booking.getCustomer()));
        bookingDto.setRoomDto(roomMapper.map(booking.getRoom()));
        return bookingDto;
    }
}
