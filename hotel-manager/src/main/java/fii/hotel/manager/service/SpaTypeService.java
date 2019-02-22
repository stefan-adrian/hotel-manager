package fii.hotel.manager.service;

import fii.hotel.manager.dto.SpaTypeDto;

import java.util.List;

public interface SpaTypeService {
    SpaTypeDto add(SpaTypeDto spaTypeDto);

    List<SpaTypeDto> getAllDtos();
}
