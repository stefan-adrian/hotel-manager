package fii.hotel.manager.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final String RECEIVER_ACCOUNT_ID = "AShmTt5td4mjBj19DVDYZlH60ErzYlOhkaK1o92jWSq9XljyGC2spSnUkbPgnJ-30P8RHt_pDNmAkvsu";
    private final String RECEIVER_ACCOUNT_SECRET = "ED7j7RfByjIquagJd0priGVxqdUPwil6xPrIlXi0mxi9hx0ju5PjLTcPuEXEpWFPnP67T9TvrF09QTeN";
    private final String EXECUTION_MODE = "sandbox";

    private Payment definePaymet() {
        // Set payer details
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        // Set redirect URLs
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:3000/cancel");
        redirectUrls.setReturnUrl("http://localhost:4200/home");

        // Set payment details
        Details details = new Details();
        details.setShipping("1");
        details.setSubtotal("5");
        details.setTax("1");

        // Payment amount
        Amount amount = new Amount();
        amount.setCurrency("USD");
        // Total must be equal to sum of shipping, tax and subtotal.
        amount.setTotal("7");
        amount.setDetails(details);


        // Transaction information
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction
                .setDescription("This is the payment transaction description.");

        // Add transaction to a list
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        // Add payment details
        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setRedirectUrls(redirectUrls);
        payment.setTransactions(transactions);
        return payment;
    }

    @Override
    public String createPayment() {
        Payment payment = definePaymet();
        APIContext apiContext = new APIContext(RECEIVER_ACCOUNT_ID, RECEIVER_ACCOUNT_SECRET, EXECUTION_MODE);

        // Create payment
        try {
            Payment createdPayment = payment.create(apiContext);

            Iterator links = createdPayment.getLinks().iterator();
            while (links.hasNext()) {
                Links link = (Links) links.next();
                if (link.getRel().equalsIgnoreCase("approval_url")) {
                    return link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }
        return null;

    }

    @Override
    public void doPay() {
        APIContext apiContext = new APIContext(RECEIVER_ACCOUNT_ID, RECEIVER_ACCOUNT_SECRET, EXECUTION_MODE);

        Payment payment = new Payment();
        payment.setId("PAYID-LT76JDY9VY41865FK8309742");

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId("CET8YXJG7FUEQ");
        Payment createdPayment;
        try {

            createdPayment = payment.execute(apiContext, paymentExecution);
        } catch (PayPalRESTException e) {
            System.out.println("Exceptie");
        }
    }

}
