package fii.hotel.manager.mapper;

import fii.hotel.manager.dto.CustomerDto;
import fii.hotel.manager.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Customer map(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setRole(customerDto.getRole());
        return customer;
    }

    public CustomerDto map(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPassword(customer.getPassword());
        customerDto.setName(customer.getName());
        customerDto.setSurname(customer.getSurname());
        customerDto.setRole(customer.getRole());
        customerDto.setQrCode(customer.getQrCode());
        return customerDto;
    }
}
