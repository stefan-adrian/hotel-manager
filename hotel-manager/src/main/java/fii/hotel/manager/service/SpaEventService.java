package fii.hotel.manager.service;

import fii.hotel.manager.dto.SpaEventDto;

public interface SpaEventService {
    SpaEventDto add(Long bookingId, SpaEventDto spaEventDto);
}
