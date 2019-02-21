package fii.hotel.manager.controller;

import fii.hotel.manager.service.CarOrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car-orders")
@Api(description = "Car orders requests")
public class CarOrderController {

    private CarOrderService carOrderService;

    @Autowired
    public CarOrderController(CarOrderService carOrderService) {
        this.carOrderService = carOrderService;
    }

}
