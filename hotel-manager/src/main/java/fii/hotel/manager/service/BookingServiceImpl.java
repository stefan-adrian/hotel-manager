package fii.hotel.manager.service;

import com.paypal.base.rest.PayPalRESTException;
import fii.hotel.manager.dto.BookingCreationDto;
import fii.hotel.manager.dto.BookingDto;
import fii.hotel.manager.exception.BookingNotFoundException;
import fii.hotel.manager.mapper.BookingMapper;
import fii.hotel.manager.mapper.CustomerMapper;
import fii.hotel.manager.model.Booking;
import fii.hotel.manager.model.Customer;
import fii.hotel.manager.model.Room;
import fii.hotel.manager.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private BookingRepository bookingRepository;
    private CustomerService customerService;
    private RoomService roomService;
    private BookingMapper bookingMapper;
    private PaymentService paymentService;
    private CustomerMapper customerMapper;
    private EmailService emailService;

    public BookingServiceImpl(BookingRepository bookingRepository, CustomerService customerService, RoomService roomService,
                              PaymentService paymentService, CustomerMapper customerMapper, EmailService emailService) {
        this.bookingRepository = bookingRepository;
        this.customerService = customerService;
        this.roomService = roomService;
        this.paymentService = paymentService;
        this.customerMapper = customerMapper;
        this.emailService = emailService;
    }

    @Autowired
    public void setBookingMapper(BookingMapper bookingMapper) {
        this.bookingMapper = bookingMapper;
    }

    @Override
    public BookingCreationDto save(BookingCreationDto bookingCreationDto) {
        Customer customer = customerService.getByEmail(bookingCreationDto.getCustomerEmail());
        Room room = roomService.getRoomByCategoryAvailableBetweenDates(bookingCreationDto.getFromTime(), bookingCreationDto.getToTime(),bookingCreationDto.getRoomCategoryName());
        Booking booking = bookingMapper.map(customer, room, bookingCreationDto);
        try {
            if(bookingCreationDto.getPayerId()!=null) {
                paymentService.executePay(bookingCreationDto.getPaymentId(), bookingCreationDto.getPayerId());
            }
            Booking savedBooking = bookingRepository.save(booking);
            emailService.sendNewBookingMail(customerMapper.map(customer),savedBooking);
            logger.debug("Booking for " + customer.getEmail() + " in room " + room.getName() + " with id " + savedBooking.getId() + " was saved in the database.");
            return bookingMapper.mapToCreationDto(savedBooking);
        }catch (PayPalRESTException e){
            System.err.println(e.getDetails());
        }
        return null;
    }

    @Override
    public Booking getById(Long id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            logger.debug("Booking with id " + booking.getId() + " has benn retrieved from database.");
            return booking;
        } else {
            logger.error("Booking with id " + id + " was not found in the database.");
            throw new BookingNotFoundException(id);
        }
    }

    private Booking getByIdFetchCarOrders(Long id) {
        Optional<Booking> bookingOptional = bookingRepository.findByIdFetchCarOrders(id);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            logger.debug("Booking with id " + booking.getId() + " has benn retrieved from database.");
            return booking;
        } else {
            logger.error("Booking with id " + id + " was not found in the database.");
            throw new BookingNotFoundException(id);
        }
    }

    @Override
    public BookingDto getBookingDtoById(Long id) {
        Booking booking = getByIdFetchCarOrders(id);
        return bookingMapper.map(booking);
    }

    @Override
    public Integer getNumberOfBookingsIn24HoursIntervalBeforeNow() {
        LocalDateTime oneDayBeforeNow=LocalDateTime.now().minusDays(1);
        return bookingRepository.getNumberOfBookingsAfterDate(oneDayBeforeNow);
    }

    @Override
    public List<Booking> getBookingsByCustomerEmail(String email) {
        return bookingRepository.getBookingsByCustomerEmail(email);
    }

    @Override
    public List<BookingDto> getBookingDtosByCustomerEmail(String email){
        List<Booking> bookings=bookingRepository.getBookingsFetchingRoomByCustomerEmail(email);
        return bookings.stream().map(bookingMapper::map).collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getActiveBookingDtosByCustomerEmail(String email){
        Set<Booking> bookings=bookingRepository.getActiveBookingsFetchingRoomByCustomerEmail(email);
        return bookings.stream().map(bookingMapper::map).collect(Collectors.toList());
    }

    @Override
    public BookingDto getCustomerNextBookingDto(String email){
        List<Booking> bookings=bookingRepository.getBookingsAfterCurrentDateFetchingRoomByCustomerEmail(email);
        return bookingMapper.map(bookings.get(0));

    }

    @Override
    public List<BookingDto> getAllBookingDtos() {
        Set<Booking> bookings=bookingRepository.getAllBookings();
        return bookings.stream().map(bookingMapper::map).collect(Collectors.toList());
    }
}
