package fii.hotel.manager.service;

import fii.hotel.manager.model.Aliment;
import fii.hotel.manager.repository.AlimentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlimentServiceImpl implements AlimentService {
    private AlimentRepository alimentRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public AlimentServiceImpl(AlimentRepository alimentRepository) {
        this.alimentRepository = alimentRepository;
    }

    @Override
    public Aliment save(Aliment aliment) {
        Aliment alimentSaved = alimentRepository.save(aliment);
        logger.debug("New aliment "+alimentSaved.getName()+" with id "+alimentSaved.getId()+" was saved in the database.");
        return alimentSaved;
    }

    @Override
    public List<Aliment> getAll() {
        List<Aliment> aliments=alimentRepository.findAll();
        logger.debug("Retrieved all aliments from database.");
        return aliments;
    }
}
