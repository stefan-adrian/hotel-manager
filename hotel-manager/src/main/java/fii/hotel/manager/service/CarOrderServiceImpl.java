package fii.hotel.manager.service;

import fii.hotel.manager.dto.CarOrderDto;
import fii.hotel.manager.mapper.CarOrderMapper;
import fii.hotel.manager.model.Booking;
import fii.hotel.manager.model.CarOrder;
import fii.hotel.manager.repository.CarOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarOrderServiceImpl implements CarOrderService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private CarOrderRepository carOrderRepository;
    private BookingService bookingService;
    private CarOrderMapper carOrderMapper;

    @Autowired
    public CarOrderServiceImpl(CarOrderRepository carOrderRepository, BookingService bookingService, CarOrderMapper carOrderMapper) {
        this.carOrderRepository = carOrderRepository;
        this.bookingService = bookingService;
        this.carOrderMapper = carOrderMapper;
    }

    private CarOrder save(CarOrder carOrder) {
        CarOrder carOrderSaved = carOrderRepository.save(carOrder);
        logger.debug("Car order " + carOrderSaved.getId() + " with booking id " + carOrderSaved.getBooking().getId() + " was saved in the database.");
        return carOrderSaved;

    }

    @Override
    public CarOrderDto add(Long bookingId, CarOrderDto carOrderDto) {
        Booking booking = bookingService.getById(bookingId);
        CarOrder carOrder = carOrderMapper.map(booking, carOrderDto);
        CarOrder carOrderSaved = save(carOrder);
        return carOrderMapper.map(carOrderSaved);
    }
}
