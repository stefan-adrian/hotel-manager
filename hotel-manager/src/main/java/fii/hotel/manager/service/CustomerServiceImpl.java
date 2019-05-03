package fii.hotel.manager.service;

import fii.hotel.manager.exception.CustomerAlreadyExistsException;
import fii.hotel.manager.exception.CustomerNotFoundException;
import fii.hotel.manager.model.Customer;
import fii.hotel.manager.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private EmailService emailService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, EmailService emailService) {
        this.customerRepository = customerRepository;
        this.emailService = emailService;
    }

    @Override
    public Customer save(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(customer.getEmail());
        if(customerOptional.isPresent()){
            logger.error("Customer with email " + customer.getEmail() + " already is in the database.");
            throw new CustomerAlreadyExistsException(customer.getEmail());
        }
        Customer customerSaved = customerRepository.save(customer);
        emailService.sendWelcomeMail(customer);
        logger.debug("Customer with email " + customerSaved.getEmail() + " and id " + customerSaved.getId() + " was saved in the database.");
        return customerSaved;
    }

    @Override
    public Customer getById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            logger.debug("Customer " + customer.getEmail() + " with id " + customer.getId() + " has benn retrieved from database.");
            return customer;
        } else {
            logger.error("Customer with id " + id + " was not found in the database.");
            throw new CustomerNotFoundException(id);
        }
    }

    @Override
    public Customer getByEmail(String email){
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            logger.debug("Customer with email " + customer.getEmail() + " has benn retrieved from database.");
            return customer;
        } else {
            logger.error("Customer with email " + email + " was not found in the database.");
            throw new CustomerNotFoundException(email);
        }
    }
}
