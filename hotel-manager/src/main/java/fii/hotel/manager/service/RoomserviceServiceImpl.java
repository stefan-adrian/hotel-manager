package fii.hotel.manager.service;

import fii.hotel.manager.dto.AllRoomservicesDto;
import fii.hotel.manager.dto.RoomserviceDto;
import fii.hotel.manager.mapper.RoomserviceMapper;
import fii.hotel.manager.model.Aliment;
import fii.hotel.manager.model.CommandStatus;
import fii.hotel.manager.model.Roomservice;
import fii.hotel.manager.model.SpaEvent;
import fii.hotel.manager.repository.RoomserviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        roomservice.setCommandStatus(CommandStatus.RECEIVED);
        Roomservice roomserviceSaved = save(roomservice);
        return roomserviceMapper.map(roomserviceSaved);
    }

    private Double calculateTotalAlimentsPrice(List<Aliment> aliments) {
        return aliments.stream().mapToDouble(aliment -> aliment.getPrice()).sum();
    }

    @Override
    public List<RoomserviceDto> getAllRoomservicesDtosForCustomerByEmail(String email) {
        Set<Roomservice> roomservices=roomserviceRepository.getRoomserviceByCustomerEmail(email);
        return roomservices.stream().map(roomserviceMapper::map).collect(Collectors.toList());
    }

    @Override
    public AllRoomservicesDto getAllRoomservices() {
        Set<Roomservice> inactiveRoomservices=roomserviceRepository.getAllRoomservicesByStatus(CommandStatus.DELIVERED);
        Set<Roomservice> activeRoomservices=roomserviceRepository.getAllRoomservicesByStatusNegation(CommandStatus.DELIVERED);
        AllRoomservicesDto allRoomservicesDto=new AllRoomservicesDto();
        allRoomservicesDto.setInactiveRoomservice(inactiveRoomservices.stream().map(roomserviceMapper::map).collect(Collectors.toList()));
        allRoomservicesDto.setActiveRoomservice(activeRoomservices.stream().map(roomserviceMapper::map).collect(Collectors.toList()));
        return allRoomservicesDto;
    }
}
