package fii.hotel.manager.mapper;

import fii.hotel.manager.dto.CustomerDto;
import fii.hotel.manager.model.Customer;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CustomerMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private Base64 base64=new Base64();

    public Customer map(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        customer.setName(new String(base64.encode(customerDto.getName().getBytes())));
        customer.setSurname(new String(base64.encode(customerDto.getSurname().getBytes())));
        customer.setRole(customerDto.getRole());
        customer.setNationality(new String(base64.encode(customerDto.getNationality().getBytes())));
        customer.setAddress(new String(base64.encode(customerDto.getAddress().getBytes())));
        customer.setIdentificationNumber(new String(base64.encode(customerDto.getIdentificationNumber().getBytes())));
        customer.setBirthday(customerDto.getBirthday());
        return customer;
    }

    public CustomerDto map(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPassword(customer.getPassword());
        customerDto.setName(new String(base64.decode(customer.getName().getBytes())));
        customerDto.setSurname(new String(base64.decode(customer.getSurname().getBytes())));
        customerDto.setRole(customer.getRole());
        customerDto.setQrCode(customer.getQrCode());
        customerDto.setNationality(new String(base64.decode(customer.getNationality().getBytes())));
        customerDto.setAddress(new String(base64.decode(customer.getAddress().getBytes())));
        customerDto.setIdentificationNumber(new String(base64.decode(customer.getIdentificationNumber().getBytes())));
        customerDto.setBirthday(customer.getBirthday());
        return customerDto;
    }
}
