package fii.hotel.manager.security.service;

import fii.hotel.manager.model.Customer;
import fii.hotel.manager.security.model.CustomerUserDetails;
import fii.hotel.manager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public CustomerUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerService.getByEmail(username);
        return new CustomerUserDetails(customer);
    }
}
