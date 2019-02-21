package fii.hotel.manager.controller;

import fii.hotel.manager.dto.CarOrderDto;
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

    @Autowired
    public BookingController(CarOrderService carOrderService) {
        this.carOrderService = carOrderService;
    }

    @ApiOperation(value = "Add a new car order for a booking")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Booking not found"),
            @ApiResponse(code = 200, message = "Car order successfully added")
    })
    @PostMapping(value = "/{bookingId}/car-orders")
    public CarOrderDto addCarOrderForBooking(@PathVariable Long bookingId, @RequestBody CarOrderDto carOrderDto){
        return carOrderService.save(bookingId,carOrderDto);
    }
}
