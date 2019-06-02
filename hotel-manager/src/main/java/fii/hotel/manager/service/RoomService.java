package fii.hotel.manager.service;

import fii.hotel.manager.dto.CategoryBookingDto;
import fii.hotel.manager.model.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface RoomService {

    Room save(Room room);

    List<Room> getAll();

    Room getById(Long id);

    Room getByIdFetchBookings(Long id);

    boolean checkIfBookingTimeAvailable(Room room, LocalDate startTime, LocalDate endTime);

    Room getRoomByCategoryAvailableBetweenDates(LocalDate arrivalDate, LocalDate departureDate, String roomCategory);

    Integer getNumberOfAvailableRoomsBetweenDates(Set<Room> rooms,LocalDate arrivalDate,LocalDate departureDate);
}
