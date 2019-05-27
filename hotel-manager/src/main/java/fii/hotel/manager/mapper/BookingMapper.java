package fii.hotel.manager.mapper;

import fii.hotel.manager.dto.*;
import fii.hotel.manager.model.Booking;
import fii.hotel.manager.model.Customer;
import fii.hotel.manager.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingMapper {

    private CustomerMapper customerMapper;
    private RoomMapper roomMapper;
    private CarOrderMapper carOrderMapper;
    private SpaEventMapper spaEventMapper;
    private RoomserviceMapper roomserviceMapper;

    @Autowired
    public BookingMapper(CustomerMapper customerMapper, RoomMapper roomMapper, CarOrderMapper carOrderMapper, RoomserviceMapper roomserviceMapper) {
        this.customerMapper = customerMapper;
        this.roomMapper = roomMapper;
        this.carOrderMapper = carOrderMapper;
        this.roomserviceMapper = roomserviceMapper;
    }

    @Autowired
    public void setSpaEventMapper(SpaEventMapper spaEventMapper) {
        this.spaEventMapper = spaEventMapper;
    }

    public Booking map(Customer customer, Room room, BookingCreationDto bookingCreationDto) {
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setRoom(room);
        booking.setRoomCleaning(bookingCreationDto.getRoomCleaning());
        booking.setPrice(bookingCreationDto.getBookingPrice());
        booking.setFromTime(bookingCreationDto.getFromTime());
        booking.setToTime(bookingCreationDto.getToTime());
        return booking;
    }

    public BookingCreationDto mapToCreationDto(Booking booking) {
        BookingCreationDto bookingCreationDto = new BookingCreationDto();
        bookingCreationDto.setId(booking.getId());
        bookingCreationDto.setRoomId(booking.getRoom().getId());
        bookingCreationDto.setCustomerEmail(booking.getCustomer().getEmail());
        bookingCreationDto.setRoomCategoryName(booking.getRoom().getCategory().getName());
        bookingCreationDto.setFromTime(booking.getFromTime());
        bookingCreationDto.setToTime(booking.getToTime());
        bookingCreationDto.setRoomCleaning(booking.getRoomCleaning());
        bookingCreationDto.setBookingPrice(booking.getPrice());
        return bookingCreationDto;
    }

    public BookingDto map(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setRoomCleaning(booking.getRoomCleaning());
        bookingDto.setFromTime(booking.getFromTime());
        bookingDto.setToTime(booking.getToTime());
        bookingDto.setPrice(booking.getPrice());
        bookingDto.setCustomerDto(customerMapper.map(booking.getCustomer()));
        bookingDto.setRoomDto(roomMapper.map(booking.getRoom()));
        List<CarOrderDto> carOrderDtos = booking.getCarOrders().stream().map(carOrderMapper::map).collect(Collectors.toList());
        bookingDto.setCarOrderDtos(carOrderDtos);
        List<SpaEventDto> spaEventDtos = booking.getSpaEvents().stream().map(spaEventMapper::map).collect(Collectors.toList());
        bookingDto.setSpaEventDtos(spaEventDtos);
        List<RoomserviceDto> roomserviceDtos = booking.getRoomservices().stream().map(roomserviceMapper::map).collect(Collectors.toList());
        bookingDto.setRoomserviceDtos(roomserviceDtos);
        return bookingDto;
    }
}
