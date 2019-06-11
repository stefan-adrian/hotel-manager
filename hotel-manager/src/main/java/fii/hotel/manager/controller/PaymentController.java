package fii.hotel.manager.controller;


import fii.hotel.manager.config.Utils;
import fii.hotel.manager.dto.PaymentCreationDto;
import fii.hotel.manager.dto.PaymentLinkDto;
import fii.hotel.manager.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = Utils.REQUEST_SOURCE)
@Api(description = "Payment requests")
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @ApiOperation(value = "Create payment")
    @ApiResponse(code = 200, message = "Payment successfully created")
    @PostMapping
    public PaymentLinkDto add(@RequestBody PaymentCreationDto paymentCreationDto) {
        return new PaymentLinkDto(paymentService.createPayment(paymentCreationDto));
    }

}
