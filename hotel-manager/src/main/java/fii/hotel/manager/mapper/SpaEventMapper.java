package fii.hotel.manager.mapper;

import fii.hotel.manager.dto.SpaEventDto;
import fii.hotel.manager.model.Booking;
import fii.hotel.manager.model.SpaEvent;
import fii.hotel.manager.model.SpaType;
import fii.hotel.manager.service.BookingService;
import fii.hotel.manager.service.SpaTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaEventMapper {

    private BookingService bookingService;
    private SpaTypeService spaTypeService;
    private SpaTypeMapper spaTypeMapper;

    @Autowired
    public SpaEventMapper(SpaTypeService spaTypeService, SpaTypeMapper spaTypeMapper) {
        this.spaTypeService = spaTypeService;
        this.spaTypeMapper = spaTypeMapper;
    }

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public SpaEvent map(SpaEventDto spaEventDto) {
        SpaEvent spaEvent = new SpaEvent();
        Booking booking = bookingService.getById(spaEventDto.getBookingId());
        SpaType spaType = spaTypeService.getById(spaEventDto.getSpaTypeDto().getId());
        spaEvent.setId(spaEventDto.getId());
        spaEvent.setStartTime(spaEventDto.getStartTime());
        spaEvent.setEndTime(spaEventDto.getEndTime());
        spaEvent.setBooking(booking);
        spaEvent.setSpaType(spaType);
        return spaEvent;
    }

    public SpaEventDto map(SpaEvent spaEvent) {
        SpaEventDto spaEventDto = new SpaEventDto();
        spaEventDto.setId(spaEvent.getId());
        spaEventDto.setStartTime(spaEvent.getStartTime());
        spaEventDto.setEndTime(spaEvent.getEndTime());
        spaEventDto.setBookingId(spaEvent.getBooking().getId());
        spaEventDto.setSpaTypeDto(spaTypeMapper.map(spaEvent.getSpaType()));
        return spaEventDto;
    }
}
