package fii.hotel.manager.service;

import fii.hotel.manager.model.Customer;

public interface EmailService {
    void sendWelcomeMail(Customer customer);
}
