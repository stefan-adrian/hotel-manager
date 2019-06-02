package fii.hotel.manager.service;

import fii.hotel.manager.exception.NoRoomsAvailableForBookingException;
import fii.hotel.manager.exception.RoomNotFoundException;
import fii.hotel.manager.mapper.CategoryBookingMapper;
import fii.hotel.manager.model.Booking;
import fii.hotel.manager.model.Room;
import fii.hotel.manager.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;
    private CategoryBookingMapper categoryBookingMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, CategoryBookingMapper categoryBookingMapper) {
        this.roomRepository = roomRepository;
        this.categoryBookingMapper = categoryBookingMapper;
    }

    @Override
    public Room save(Room room) {
        Room roomSaved = roomRepository.save(room);
        logger.debug("New room " + roomSaved.getName() + " with id " + roomSaved.getId() + " was saved in the database.");
        return roomSaved;
    }

    @Override
    public List<Room> getAll() {
        List<Room> rooms = roomRepository.findAll();
        logger.debug("Retrieved all rooms from database.");
        return rooms;
    }

    @Override
    public Room getById(Long id) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            logger.debug("Room " + room.getName() + " with id " + room.getId() + " has benn retrieved from database.");
            return room;
        } else {
            logger.error("Room with id " + id + " was not found in the database.");
            throw new RoomNotFoundException(id);
        }
    }

    @Override
    public Room getByIdFetchBookings(Long id) {
        Optional<Room> roomOptional = roomRepository.findByIdFetchBookings(id);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            logger.debug("Room " + room.getName() + " with id " + room.getId() + " has benn retrieved from database.");
            return room;
        } else {
            logger.error("Room with id " + id + " was not found in the database.");
            throw new RoomNotFoundException(id);
        }
    }

    @Override
    public boolean checkIfBookingTimeAvailable(Room room, LocalDate startTime, LocalDate endTime) {
        Set<Booking> bookings = room.getBookings();
        for (Booking booking : bookings) {
            if (checkIfStartTimeNotAvailable(startTime, booking.getFromTime(), booking.getToTime())
                    || checkIfEndTimeNotAvailable(endTime, booking.getFromTime(), booking.getToTime())
                    || checkIfBookingDateIsBetweenWantedDates(startTime, endTime, booking)
                    || startTime.compareTo(endTime) >= 0) {
                logger.debug("Room with id " + room.getId() + " cant be booked between " + startTime + " and " + endTime
                        + " because is already booked between " + booking.getFromTime() + " and " + booking.getToTime());
                return false;
            }
        }
        return true;

    }

    private boolean checkIfStartTimeNotAvailable(LocalDate toCheckDate, LocalDate startTime, LocalDate endTime) {
        return toCheckDate.compareTo(startTime) >= 0 && toCheckDate.compareTo(endTime) < 0;
    }

    private boolean checkIfEndTimeNotAvailable(LocalDate toCheckDate, LocalDate startTime, LocalDate endTime) {
        return toCheckDate.compareTo(startTime) > 0 && toCheckDate.compareTo(endTime) <= 0;
    }

    private boolean checkIfBookingDateIsBetweenWantedDates(LocalDate startDate, LocalDate endDate, Booking booking) {
        return startDate.compareTo(booking.getFromTime()) <= 0 && endDate.compareTo(booking.getToTime()) >= 0;
    }

    @Override
    public Room getRoomByCategoryAvailableBetweenDates(LocalDate arrivalDate, LocalDate departureDate, String roomCategory) {
        Set<Room> rooms = roomRepository.getRoomsByCategoryAvailableBetweenDates(roomCategory);
        for (Room room : rooms) {
            if (checkIfBookingTimeAvailable(room, arrivalDate, departureDate)) {
                return room;
            }
        }
        logger.error("There are no rooms available for booking between " + arrivalDate + " and " + departureDate);
        throw new NoRoomsAvailableForBookingException(arrivalDate, departureDate);
    }

    @Override
    public Integer getNumberOfAvailableRoomsBetweenDates(Set<Room> rooms,LocalDate arrivalDate,LocalDate departureDate) {
        return (int) rooms.stream().filter(room -> checkIfBookingTimeAvailable(room, arrivalDate, departureDate)).count();
    }
}
