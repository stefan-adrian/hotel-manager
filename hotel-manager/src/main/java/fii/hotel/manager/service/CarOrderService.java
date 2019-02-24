package fii.hotel.manager.service;

import fii.hotel.manager.dto.CarOrderDto;

public interface CarOrderService {
    CarOrderDto add(Long bookingId, CarOrderDto carOrderDto);
}
