package fii.hotel.manager.service;

import fii.hotel.manager.dto.CategoryBookingDto;
import fii.hotel.manager.exception.NoRoomsAvailableForBookingException;
import fii.hotel.manager.exception.RoomAlreadyBookedException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;

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
    public void checkThatRoomBookingTimeDoesNotOverlap(Room room, LocalDate startTime, LocalDate endTime) {
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

    private boolean checkIfTimeInBetween(LocalDate toCheckDate, LocalDate startTime, LocalDate endTime) {
        return toCheckDate.compareTo(startTime) >= 0 && toCheckDate.compareTo(endTime) <= 0;
    }

    @Override
    public List<CategoryBookingDto> getAllCategoriesAvailableBetweenDates(LocalDate arrivalDate, LocalDate departureDate) {
        List<CategoryBookingDto> categoryBookingDtos=new ArrayList<>();
        long bookingDays= DAYS.between(arrivalDate,departureDate);
        roomRepository.getRoomsAvailableBetweenDates(arrivalDate,departureDate).forEach(room -> {
            if(categoryBookingDtos.size()==0||!categoryBookingDtos.get(categoryBookingDtos.size()-1).getName().equals(room.getCategory().getName())){
                CategoryBookingDto categoryBookingDto=categoryBookingMapper.map(room.getCategory());
                categoryBookingDto.setTotalBookingPrice(room.getCategory().getPrice()*bookingDays);
                categoryBookingDtos.add(categoryBookingDto);
            } else {
                CategoryBookingDto categoryBookingDto=categoryBookingDtos.get(categoryBookingDtos.size()-1);
                categoryBookingDto.setAvailableRooms(categoryBookingDto.getAvailableRooms()+1);
            }
        });
        if(categoryBookingDtos.size()==0){
            throw new NoRoomsAvailableForBookingException(arrivalDate,departureDate);
        }
        return categoryBookingDtos;
    }
}
