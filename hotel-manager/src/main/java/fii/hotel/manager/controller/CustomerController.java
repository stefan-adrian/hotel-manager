package fii.hotel.manager.controller;

import fii.hotel.manager.config.Utils;
import fii.hotel.manager.dto.BookingCreationDto;
import fii.hotel.manager.dto.CustomerDto;
import fii.hotel.manager.dto.RoomserviceDto;
import fii.hotel.manager.mapper.CustomerMapper;
import fii.hotel.manager.model.Booking;
import fii.hotel.manager.model.Customer;
import fii.hotel.manager.service.BookingService;
import fii.hotel.manager.service.CustomerService;
import fii.hotel.manager.service.RoomserviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = Utils.REQUEST_SOURCE)
@Api(description = "Customers requests")
public class CustomerController {
    private CustomerService customerService;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @ApiOperation(value = "Add a new customer")
    @ApiResponse(code = 200, message = "Customer successfully added")
    @PostMapping
    public CustomerDto add(@RequestBody CustomerDto customerDto) {
        return customerService.add(customerDto);
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

    @ApiOperation(value = "Get customer with specified email")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Customer not found"),
            @ApiResponse(code = 200, message = "Retrieved customer with the asked email")
    })
    @GetMapping(value = "/email")
    public CustomerDto getByEmail(@RequestParam String email) {
        Customer customer = customerService.getByEmail(email);
        return customerMapper.map(customer);
    }
}
