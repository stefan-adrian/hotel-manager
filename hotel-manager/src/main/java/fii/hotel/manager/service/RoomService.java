package fii.hotel.manager.service;

import fii.hotel.manager.dto.CategoryBookingDto;
import fii.hotel.manager.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    Room save(Room room);

    List<Room> getAll();

    Room getById(Long id);

    Room getByIdFetchBookings(Long id);

    void checkThatRoomBookingTimeDoesNotOverlap(Room room, LocalDate startTime, LocalDate endTime);

    List<CategoryBookingDto> getAllCategoriesAvailableBetweenDates(LocalDate arrivalDate, LocalDate departureDate);
}
