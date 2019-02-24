package fii.hotel.manager.service;

import fii.hotel.manager.dto.SpaTypeDto;
import fii.hotel.manager.model.SpaType;

import java.util.List;

public interface SpaTypeService {
    SpaTypeDto add(SpaTypeDto spaTypeDto);

    List<SpaTypeDto> getAllDtos();

    SpaType getById(Long id);

}
