package fii.hotel.manager.mapper;

import fii.hotel.manager.dto.SpaTypeDto;
import fii.hotel.manager.model.SpaType;
import org.springframework.stereotype.Service;

@Service
public class SpaTypeMapper {

    public SpaType map(SpaTypeDto spaTypeDto) {
        SpaType spaType = new SpaType();
        spaType.setId(spaTypeDto.getId());
        spaType.setName(spaTypeDto.getName());
        spaType.setPrice(spaTypeDto.getPrice());
        return spaType;
    }

    public SpaTypeDto map(SpaType spaType) {
        SpaTypeDto spaTypeDto = new SpaTypeDto();
        spaTypeDto.setId(spaType.getId());
        spaTypeDto.setName(spaType.getName());
        spaTypeDto.setPrice(spaType.getPrice());
        return spaTypeDto;
    }
}
