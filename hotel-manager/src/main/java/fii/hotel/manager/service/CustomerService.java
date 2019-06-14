package fii.hotel.manager.service;

import fii.hotel.manager.dto.CustomerDto;
import fii.hotel.manager.model.Customer;

public interface CustomerService {
    CustomerDto add(CustomerDto customerDto);

    Customer getById(Long id);

    Customer getByEmail(String email);
}
