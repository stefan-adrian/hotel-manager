package fii.hotel.manager.service;

import com.paypal.base.rest.PayPalRESTException;
import fii.hotel.manager.dto.PaymentCreationDto;

public interface PaymentService {

    String createPayment(PaymentCreationDto paymentCreationDto);

    void executePay(String paymentId,String payerId) throws PayPalRESTException;

}
