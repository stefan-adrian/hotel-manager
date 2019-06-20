package fii.hotel.manager.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import fii.hotel.manager.config.Utils;
import fii.hotel.manager.dto.PaymentCreationDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final String RECEIVER_ACCOUNT_ID = "AShmTt5td4mjBj19DVDYZlH60ErzYlOhkaK1o92jWSq9XljyGC2spSnUkbPgnJ-30P8RHt_pDNmAkvsu";
    private final String RECEIVER_ACCOUNT_SECRET = "ED7j7RfByjIquagJd0priGVxqdUPwil6xPrIlXi0mxi9hx0ju5PjLTcPuEXEpWFPnP67T9TvrF09QTeN";
    private final String EXECUTION_MODE = "sandbox";
    @Override
    public String createPayment(PaymentCreationDto paymentCreationDto) {
        Payment payment = definePaymet(paymentCreationDto);
        APIContext apiContext = new APIContext(RECEIVER_ACCOUNT_ID, RECEIVER_ACCOUNT_SECRET, EXECUTION_MODE);
        try {
            Payment createdPayment = payment.create(apiContext);
            for (Links link : createdPayment.getLinks()) {
                if (link.getRel().equalsIgnoreCase("approval_url")) {
                    return link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }
        return null;
    }

    private Payment definePaymet(PaymentCreationDto paymentCreationDto) {
        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(createPayer());
        payment.setRedirectUrls(createRedirectUrls());
        payment.setTransactions(createPaymentTransactions(paymentCreationDto));
        return payment;
    }

    private Payer createPayer(){
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        return payer;
    }

    private RedirectUrls createRedirectUrls(){
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(Utils.REQUEST_SOURCE + "/home");
        redirectUrls.setReturnUrl(Utils.REQUEST_SOURCE + "/profile/bookings");
        return  redirectUrls;
    }

    private Details createPaymentDetails(PaymentCreationDto paymentCreationDto){
        Details details = new Details();
        details.setShipping("0");
        details.setSubtotal(paymentCreationDto.getAmount().toString());
        details.setTax("0");
        return details;
    }

    private Amount createPaymentAmount(PaymentCreationDto paymentCreationDto){
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(paymentCreationDto.getAmount().toString());
        amount.setDetails(createPaymentDetails(paymentCreationDto));
        return amount;
    }

    private List<Transaction> createPaymentTransactions(PaymentCreationDto paymentCreationDto){
        Transaction transaction = new Transaction();
        transaction.setAmount(createPaymentAmount(paymentCreationDto));
        transaction
                .setDescription("Payment for " + paymentCreationDto.getItemName() + ".");
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        return  transactions;
    }

    @Override
    public void executePay(String paymentId, String payerId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(RECEIVER_ACCOUNT_ID, RECEIVER_ACCOUNT_SECRET, EXECUTION_MODE);

        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        payment.execute(apiContext, paymentExecution);
    }

}
