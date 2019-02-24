package fii.hotel.manager.service;

import fii.hotel.manager.dto.RoomserviceDto;
import fii.hotel.manager.mapper.RoomserviceMapper;
import fii.hotel.manager.model.Aliment;
import fii.hotel.manager.model.Roomservice;
import fii.hotel.manager.model.SpaEvent;
import fii.hotel.manager.repository.RoomserviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomserviceServiceImpl implements RoomserviceService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private RoomserviceRepository roomserviceRepository;
    private RoomserviceMapper roomserviceMapper;

    @Autowired
    public RoomserviceServiceImpl(RoomserviceRepository roomserviceRepository, RoomserviceMapper roomserviceMapper) {
        this.roomserviceRepository = roomserviceRepository;
        this.roomserviceMapper = roomserviceMapper;
    }

    private Roomservice save(Roomservice roomservice) {
        Roomservice roomserviceSaved = roomserviceRepository.save(roomservice);
        logger.debug("Roomservice " + roomserviceSaved.getId() + " with booking id " + roomserviceSaved.getBooking().getId() + " was saved in the database.");
        return roomserviceSaved;

    }

    @Override
    public RoomserviceDto add(Long bookingId, RoomserviceDto roomserviceDto) {
        roomserviceDto.setBookingId(bookingId);
        Roomservice roomservice = roomserviceMapper.map(roomserviceDto);
        roomservice.setTotalCommandPrice(calculateTotalAlimentsPrice(roomservice.getAliments()));
        Roomservice roomserviceSaved = save(roomservice);
        return roomserviceMapper.map(roomserviceSaved);
    }

    private Double calculateTotalAlimentsPrice(List<Aliment> aliments) {
        return 0.0;
    }
}
