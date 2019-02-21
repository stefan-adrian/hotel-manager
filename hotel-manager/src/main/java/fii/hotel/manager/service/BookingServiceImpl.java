package fii.hotel.manager.service;

import fii.hotel.manager.dto.BookingCreationDto;
import fii.hotel.manager.exception.BookingNotFoundException;
import fii.hotel.manager.model.Booking;
import fii.hotel.manager.model.Customer;
import fii.hotel.manager.model.Room;
import fii.hotel.manager.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private BookingRepository bookingRepository;
    private CustomerService customerService;
    private RoomService roomService;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, CustomerService customerService, RoomService roomService) {
        this.bookingRepository = bookingRepository;
        this.customerService = customerService;
        this.roomService = roomService;
    }

    @Override
    public Booking save(Long customerId, BookingCreationDto bookingCreationDto) {
        Customer customer = customerService.getById(customerId);
        Room room = roomService.getById(bookingCreationDto.getRoomId());
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setRoom(room);
        booking.setRoomCleaning(bookingCreationDto.getRoomCleaning());
        booking.setFromTime(bookingCreationDto.getFromTime());
        booking.setToTime(bookingCreationDto.getToTime());
        Booking savedBooking = bookingRepository.save(booking);
        logger.debug("Booking for " + customer.getEmail() + " in room " + room.getName() + " with id " + savedBooking.getId() + " was saved in the database.");
        return savedBooking;
    }

    @Override
    public Booking getById(Long id) {
        Optional<Booking> bookingOptional=bookingRepository.findById(id);
        if(bookingOptional.isPresent()){
            Booking booking=bookingOptional.get();
            logger.debug("Booking with id " + booking.getId() + " has benn retrieved from database.");
            return booking;
        } else {
            logger.error("Booking with id " + id + " was not found in the database.");
            throw new BookingNotFoundException(id);
        }
    }
}
