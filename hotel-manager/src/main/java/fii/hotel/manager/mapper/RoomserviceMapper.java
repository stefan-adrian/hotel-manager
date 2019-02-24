package fii.hotel.manager.mapper;

import fii.hotel.manager.dto.AlimentDto;
import fii.hotel.manager.dto.RoomserviceDto;
import fii.hotel.manager.model.Aliment;
import fii.hotel.manager.model.Roomservice;
import fii.hotel.manager.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomserviceMapper {

    private AlimentMapper alimentMapper;
    private BookingService bookingService;

    @Autowired
    public RoomserviceMapper(AlimentMapper alimentMapper, BookingService bookingService) {
        this.alimentMapper = alimentMapper;
        this.bookingService = bookingService;
    }

    public Roomservice map(RoomserviceDto roomserviceDto) {
        Roomservice roomservice = new Roomservice();
        roomservice.setId(roomserviceDto.getId());
        roomservice.setTimeOfOrder(roomserviceDto.getTimeOfOrder());
        roomservice.setTotalCommandPrice(roomserviceDto.getTotalCommandPrice());
        List<Aliment> aliments = roomserviceDto.getAlimentDtos().stream().map(alimentMapper::map).collect(Collectors.toList());
        roomservice.setAliments(aliments);
        roomservice.setBooking(bookingService.getById(roomserviceDto.getBookingId()));
        return roomservice;
    }

    public RoomserviceDto map(Roomservice roomservice) {
        RoomserviceDto roomserviceDto = new RoomserviceDto();
        roomserviceDto.setId(roomservice.getId());
        roomserviceDto.setTimeOfOrder(roomservice.getTimeOfOrder());
        roomserviceDto.setTotalCommandPrice(roomservice.getTotalCommandPrice());
        List<AlimentDto> alimentDtos = roomservice.getAliments().stream().map(alimentMapper::map).collect(Collectors.toList());
        roomserviceDto.setAlimentDtos(alimentDtos);
        roomserviceDto.setBookingId(roomservice.getBooking().getId());
        return roomserviceDto;
    }
}
