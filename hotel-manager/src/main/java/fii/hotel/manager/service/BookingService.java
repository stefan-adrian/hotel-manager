package fii.hotel.manager.service;

import fii.hotel.manager.dto.BookingCreationDto;
import fii.hotel.manager.model.Booking;

public interface BookingService {
    Booking save(Long customerId, BookingCreationDto bookingCreationDto);
}
