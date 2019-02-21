package fii.hotel.manager.controller;

import fii.hotel.manager.dto.BookingDto;
import fii.hotel.manager.dto.CarOrderDto;
import fii.hotel.manager.service.BookingService;
import fii.hotel.manager.service.CarOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@Api(description = "Bookings requests")
public class BookingController {
    private CarOrderService carOrderService;
    private BookingService bookingService;

    @Autowired
    public BookingController(CarOrderService carOrderService, BookingService bookingService) {
        this.carOrderService = carOrderService;
        this.bookingService = bookingService;
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
        return carOrderService.save(bookingId, carOrderDto);
    }
}
