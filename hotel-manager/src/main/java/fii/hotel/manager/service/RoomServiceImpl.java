package fii.hotel.manager.service;

import fii.hotel.manager.exception.RoomAlreadyBookedException;
import fii.hotel.manager.exception.RoomNotFoundException;
import fii.hotel.manager.model.Booking;
import fii.hotel.manager.model.Room;
import fii.hotel.manager.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
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
    public void checkThatRoomBookingTimeDoesNotOverlap(Room room, LocalDateTime startTime, LocalDateTime endTime) {
        Set<Booking> bookings = room.getBookings();
        for (Booking booking : bookings) {
            if (checkIfTimeInBetween(booking.getToTime(), startTime, endTime)
                    || checkIfTimeInBetween(booking.getFromTime(), startTime, endTime)
                    || checkIfTimeInBetween(startTime, booking.getFromTime(), booking.getToTime())
                    || checkIfTimeInBetween(endTime, booking.getFromTime(), booking.getToTime())) {
                logger.error("Room with id " + room.getId() + " cant be booked between " + startTime + " and " + endTime
                        + " because is already booked between " + booking.getFromTime() + " and " + booking.getToTime());
                throw new RoomAlreadyBookedException(startTime, endTime);
            }
        }

    }

    private boolean checkIfTimeInBetween(LocalDateTime toCheckDate, LocalDateTime startTime, LocalDateTime endTime) {
        return toCheckDate.compareTo(startTime) >= 0 && toCheckDate.compareTo(endTime) <= 0;
    }
}
