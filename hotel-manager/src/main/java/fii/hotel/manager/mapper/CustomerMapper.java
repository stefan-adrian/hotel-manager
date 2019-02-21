package fii.hotel.manager.mapper;

import fii.hotel.manager.dto.CustomerDto;
import fii.hotel.manager.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer map(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        return customer;
    }

    public CustomerDto map(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPassword(customer.getPassword());
        customerDto.setName(customer.getName());
        customerDto.setSurname(customer.getSurname());
        return customerDto;
    }
}
