package fii.hotel.manager.controller;

import fii.hotel.manager.dto.CustomerDto;
import fii.hotel.manager.mapper.CustomerMapper;
import fii.hotel.manager.model.Customer;
import fii.hotel.manager.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
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
        Customer customer = customerMapper.map(customerDto);
        customer = customerService.save(customer);
        return customerMapper.map(customer);
    }
}
