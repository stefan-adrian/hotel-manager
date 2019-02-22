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
        spaType.setFloor(spaTypeDto.getFloor());
        spaType.setRoomName(spaTypeDto.getRoomName());
        return spaType;
    }

    public SpaTypeDto map(SpaType spaType) {
        SpaTypeDto spaTypeDto = new SpaTypeDto();
        spaTypeDto.setId(spaType.getId());
        spaTypeDto.setName(spaType.getName());
        spaTypeDto.setPrice(spaType.getPrice());
        spaTypeDto.setFloor(spaType.getFloor());
        spaTypeDto.setRoomName(spaType.getRoomName());
        return spaTypeDto;
    }
}
