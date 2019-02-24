package fii.hotel.manager.service;

import fii.hotel.manager.dto.SpaEventDto;
import fii.hotel.manager.mapper.SpaEventMapper;
import fii.hotel.manager.model.SpaEvent;
import fii.hotel.manager.repository.SpaEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaEventServiceImpl implements SpaEventService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private SpaEventRepository spaEventRepository;
    private SpaEventMapper spaEventMapper;

    @Autowired
    public SpaEventServiceImpl(SpaEventRepository spaEventRepository, SpaEventMapper spaEventMapper) {
        this.spaEventRepository = spaEventRepository;
        this.spaEventMapper = spaEventMapper;
    }

    private SpaEvent save(SpaEvent spaEvent) {
        SpaEvent spaEventSaved = spaEventRepository.save(spaEvent);
        logger.debug("Spa event " + spaEventSaved.getId() + " with booking id " + spaEventSaved.getBooking().getId() + " was saved in the database.");
        return spaEventSaved;

    }

    @Override
    public SpaEventDto add(Long bookingId, SpaEventDto spaEventDto) {
        spaEventDto.setBookingId(bookingId);
        SpaEvent spaEvent = spaEventMapper.map(spaEventDto);
        SpaEvent spaEventSaved = save(spaEvent);
        return spaEventMapper.map(spaEventSaved);

    }
}
