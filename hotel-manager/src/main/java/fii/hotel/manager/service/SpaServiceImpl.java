package fii.hotel.manager.service;

import fii.hotel.manager.dto.SpaTypeDto;
import fii.hotel.manager.mapper.SpaTypeMapper;
import fii.hotel.manager.model.SpaType;
import fii.hotel.manager.repository.SpaTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpaServiceImpl implements SpaTypeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private SpaTypeRepository spaTypeRepository;
    private SpaTypeMapper spaTypeMapper;

    @Autowired
    public SpaServiceImpl(SpaTypeRepository spaTypeRepository, SpaTypeMapper spaTypeMapper) {
        this.spaTypeRepository = spaTypeRepository;
        this.spaTypeMapper = spaTypeMapper;
    }

    private SpaType save(SpaType spaType) {
        SpaType spaTypeSaved = spaTypeRepository.save(spaType);
        logger.debug("New spa type " + spaTypeSaved.getName() + " with id " + spaTypeSaved.getId() + " was saved in the database.");
        return spaTypeSaved;

    }

    @Override
    public SpaTypeDto add(SpaTypeDto spaTypeDto) {
        SpaType spaType = spaTypeMapper.map(spaTypeDto);
        spaType = save(spaType);
        return spaTypeMapper.map(spaType);
    }

    private List<SpaType> getAll() {
        List<SpaType> spaTypes = spaTypeRepository.findAll();
        logger.debug("Retrieved all spa types from database.");
        return spaTypes;
    }

    @Override
    public List<SpaTypeDto> getAllDtos() {
        return getAll().stream().map(spaTypeMapper::map).collect(Collectors.toList());
    }
}
