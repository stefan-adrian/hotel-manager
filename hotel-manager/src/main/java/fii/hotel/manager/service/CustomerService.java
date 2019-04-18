package fii.hotel.manager.service;

import fii.hotel.manager.model.Customer;

public interface CustomerService {
    Customer save(Customer customer);

    Customer getById(Long id);

    Customer getByEmail(String email);
}
