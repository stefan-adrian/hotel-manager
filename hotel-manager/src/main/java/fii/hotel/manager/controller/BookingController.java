package fii.hotel.manager.controller;

import fii.hotel.manager.config.Utils;
import fii.hotel.manager.dto.*;
import fii.hotel.manager.service.BookingService;
import fii.hotel.manager.service.CarOrderService;
import fii.hotel.manager.service.RoomserviceService;
import fii.hotel.manager.service.SpaEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins = Utils.REQUEST_SOURCE)
@Api(description = "Bookings requests")
public class BookingController {
    private CarOrderService carOrderService;
    private BookingService bookingService;
    private SpaEventService spaEventService;
    private RoomserviceService roomserviceService;

    @Autowired
    public BookingController(CarOrderService carOrderService, BookingService bookingService,
                             SpaEventService spaEventService, RoomserviceService roomserviceService) {
        this.carOrderService = carOrderService;
        this.bookingService = bookingService;
        this.spaEventService = spaEventService;
        this.roomserviceService = roomserviceService;
    }

    @ApiOperation(value = "Get booking with specified id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Booking not found"),
            @ApiResponse(code = 200, message = "Retrieved booking with the asked id")
    })
    @GetMapping(value = "/{id}")
    public BookingDto getById(@PathVariable Long id) {
        return bookingService.getBookingDtoById(id);
    }

    @ApiOperation(value = "Add a new car order for a booking")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Booking not found"),
            @ApiResponse(code = 200, message = "Car order successfully added")
    })
    @PostMapping(value = "/{bookingId}/car-orders")
    public CarOrderDto addCarOrderForBooking(@PathVariable Long bookingId, @RequestBody CarOrderDto carOrderDto) {
        return carOrderService.add(bookingId, carOrderDto);
    }

    @ApiOperation(value = "Add a new spa event for a booking")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Booking not found"),
            @ApiResponse(code = 200, message = "Spa event successfully added")
    })
    @PostMapping(value = "/{bookingId}/spa-events")
    public SpaEventDto addSpaEventForBooking(@PathVariable Long bookingId, @RequestBody SpaEventDto spaEventDto) {
        return spaEventService.add(bookingId, spaEventDto);
    }

    @ApiOperation(value = "Add a new roomservice for a booking")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Booking not found"),
            @ApiResponse(code = 200, message = "Roomservice successfully added")
    })
    @PostMapping(value = "/{bookingId}/roomservices")
    public RoomserviceDto addRoomserviceForBooking(@PathVariable Long bookingId, @RequestBody RoomserviceDto roomserviceDto) {
        return roomserviceService.add(bookingId, roomserviceDto);
    }

    @ApiOperation(value = "Add a new booking")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Customer or room not found"),
            @ApiResponse(code = 200, message = "Booking successfully added")
    })
    @PostMapping
    public BookingCreationDto addBooking(@RequestBody BookingCreationDto bookingCreationDto) {
        return bookingService.save(bookingCreationDto);
    }

    @ApiOperation(value = "Get list of all customer bookings")
    @ApiResponse(code = 200, message = "List of all customer bookings")
    @GetMapping(value="/customer")
    public List<BookingDto> getAllCustomerBookings(@RequestParam String email) {
        return bookingService.getBookingDtosByCustomerEmail(email);
    }

    @ApiOperation(value = "Get next booking for customer")
    @ApiResponse(code = 200, message = "Next booking for customer")
    @GetMapping(value="/customer-next")
    public BookingDto getNextCustomerBooking(@RequestParam String email) {
        return bookingService.getCustomerNextBookingDto(email);
    }
}
