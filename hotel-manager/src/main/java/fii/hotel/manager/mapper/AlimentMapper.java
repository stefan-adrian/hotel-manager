package fii.hotel.manager.mapper;

import fii.hotel.manager.dto.AlimentDto;
import fii.hotel.manager.model.Aliment;
import org.springframework.stereotype.Service;

@Service
public class AlimentMapper {

    public Aliment map(AlimentDto alimentDto) {
        Aliment aliment = new Aliment();
        aliment.setId(alimentDto.getId());
        aliment.setName(alimentDto.getName());
        aliment.setPrice(alimentDto.getPrice());
        return aliment;
    }

    public AlimentDto map(Aliment aliment) {
        AlimentDto alimentDto = new AlimentDto();
        alimentDto.setId(aliment.getId());
        alimentDto.setName(aliment.getName());
        alimentDto.setPrice(aliment.getPrice());
        return alimentDto;
    }
}
