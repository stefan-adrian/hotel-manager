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
        customer.setNationality(customerDto.getNationality());
        customer.setAddress(customerDto.getAddress());
        customer.setIdentificationNumber(customerDto.getIdentificationNumber());
        customer.setBirthday(customerDto.getBirthday());
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
        customerDto.setNationality(customer.getNationality());
        customerDto.setAddress(customer.getAddress());
        customerDto.setIdentificationNumber(customer.getIdentificationNumber());
        customerDto.setBirthday(customer.getBirthday());
        return customerDto;
    }
}
