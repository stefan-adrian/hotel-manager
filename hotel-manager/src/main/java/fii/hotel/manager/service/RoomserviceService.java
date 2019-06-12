package fii.hotel.manager.service;

import fii.hotel.manager.dto.RoomserviceDto;

import java.util.List;

public interface RoomserviceService {
    RoomserviceDto add(Long bookingId, RoomserviceDto roomserviceDto);

    List<RoomserviceDto> getAllRoomservicesDtosForCustomerByEmail(String email);
}
