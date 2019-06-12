package fii.hotel.manager.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import fii.hotel.manager.exception.CustomerAlreadyExistsException;
import fii.hotel.manager.exception.CustomerNotFoundException;
import fii.hotel.manager.model.Customer;
import fii.hotel.manager.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private EmailService emailService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, EmailService emailService) {
        this.customerRepository = customerRepository;
        this.emailService = emailService;
    }

    @Override
    public Customer save(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(customer.getEmail());
        if(customerOptional.isPresent()){
            System.out.println(passwordEncoder.encode(customerOptional.get().getPassword()));
            logger.error("Customer with email " + customer.getEmail() + " already is in the database.");
            throw new CustomerAlreadyExistsException(customer.getEmail());
        }
        customer.setQrCode(geenerateQRCodeImage(customer.getEmail(),350,350));
        Customer customerSaved = customerRepository.save(customer);
        emailService.sendSimpleWelcomeMail(customer);
        logger.debug("Customer with email " + customerSaved.getEmail() + " and id " + customerSaved.getId() + " was saved in the database.");
        return customerSaved;
    }

    private byte[] geenerateQRCodeImage(String text, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream jpgOutputStream = new ByteArrayOutputStream();
        try {
            MatrixToImageWriter.writeToStream(bitMatrix, "JPG", jpgOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] jpgData = jpgOutputStream.toByteArray();
        return jpgData;
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
