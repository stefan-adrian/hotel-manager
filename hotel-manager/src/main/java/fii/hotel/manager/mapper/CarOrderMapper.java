package fii.hotel.manager.mapper;

import fii.hotel.manager.dto.CarOrderDto;
import fii.hotel.manager.model.Booking;
import fii.hotel.manager.model.CarOrder;
import org.springframework.stereotype.Service;

@Service
public class CarOrderMapper {
    public CarOrder map(Booking booking, CarOrderDto carOrderDto){
        CarOrder carOrder=new CarOrder();
        carOrder.setId(carOrderDto.getId());
        carOrder.setToTime(carOrderDto.getToTime());
        carOrder.setBooking(booking);
        return carOrder;
    }

    public CarOrderDto map(CarOrder carOrder){
        CarOrderDto carOrderDto=new CarOrderDto();
        carOrderDto.setId(carOrder.getId());
        carOrderDto.setToTime(carOrder.getToTime());
        carOrderDto.setBookingId(carOrder.getBooking().getId());
        return carOrderDto;
    }
}
