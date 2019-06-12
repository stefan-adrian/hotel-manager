package fii.hotel.manager.service;

import fii.hotel.manager.dto.BookingCreationDto;
import fii.hotel.manager.dto.BookingDto;
import fii.hotel.manager.model.Booking;

import java.util.List;

public interface BookingService {
    BookingCreationDto save(BookingCreationDto bookingCreationDto);

    Booking getById(Long id);

    BookingDto getBookingDtoById(Long id);

    Integer getNumberOfBookingsIn24HoursIntervalBeforeNow();

    List<Booking> getBookingsByCustomerEmail(String email);

    List<BookingDto> getBookingDtosByCustomerEmail(String email);

    BookingDto getCustomerNextBookingDto(String email);
}
