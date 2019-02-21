package fii.hotel.manager.controller;

import fii.hotel.manager.dto.BookingCreationDto;
import fii.hotel.manager.dto.CustomerDto;
import fii.hotel.manager.mapper.CustomerMapper;
import fii.hotel.manager.model.Booking;
import fii.hotel.manager.model.Customer;
import fii.hotel.manager.service.BookingService;
import fii.hotel.manager.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@Api(description = "Customers requests")
public class CustomerController {
    private CustomerService customerService;
    private CustomerMapper customerMapper;
    private BookingService bookingService;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerMapper customerMapper, BookingService bookingService) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
        this.bookingService = bookingService;
    }

    @ApiOperation(value = "Add a new customer")
    @ApiResponse(code = 200, message = "Customer successfully added")
    @PostMapping
    public CustomerDto add(@RequestBody CustomerDto customerDto) {
        Customer customer = customerMapper.map(customerDto);
        customer = customerService.save(customer);
        return customerMapper.map(customer);
    }

    @ApiOperation(value = "Get customer with specified id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Customer not found"),
            @ApiResponse(code = 200, message = "Retrieved customer with the asked id")
    })
    @GetMapping(value = "/{id}")
    public CustomerDto getById(@PathVariable Long id) {
        Customer customer = customerService.getById(id);
        return customerMapper.map(customer);
    }

    @ApiOperation(value = "Add a new booking for a customer")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Customer or room not found"),
            @ApiResponse(code = 200, message = "Booking successfully added")
    })
    @PostMapping(value = "/{customerId}/bookings")
    public BookingCreationDto addBookingForCustomer(@PathVariable Long customerId, @RequestBody BookingCreationDto bookingCreationDto) {
        return bookingService.save(customerId, bookingCreationDto);
    }
}
