package fii.hotel.manager.service;

import fii.hotel.manager.dto.RoomserviceDto;

public interface RoomserviceService {
    RoomserviceDto add(Long bookingId, RoomserviceDto roomserviceDto);
}
