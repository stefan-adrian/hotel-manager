package fii.hotel.manager.service;

import fii.hotel.manager.exception.AlimentNotFoundException;
import fii.hotel.manager.model.Aliment;
import fii.hotel.manager.repository.AlimentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
        logger.debug("New aliment " + alimentSaved.getName() + " with id " + alimentSaved.getId() + " was saved in the database.");
        return alimentSaved;
    }

    @Override
    public Aliment save(Aliment aliment, MultipartFile image) {
        try {
            aliment.setImage(image.getBytes());
        } catch (IOException e) {
            logger.error("Image for aliment with id " + aliment.getId() + " could not be saved in the database.");
        }
        aliment = alimentRepository.save(aliment);
        return aliment;
    }

    @Override
    public List<Aliment> getAll() {
        List<Aliment> aliments = alimentRepository.findAll();
        logger.debug("Retrieved all aliments from database.");
        return aliments;
    }

    @Override
    public Aliment getById(Long id) {
        Optional<Aliment> alimentOptional = alimentRepository.findById(id);
        if (alimentOptional.isPresent()) {
            Aliment aliment = alimentOptional.get();
            logger.debug("Aliment " + aliment.getName() + " with id " + aliment.getId() + " has benn retrieved from database.");
            return aliment;
        } else {
            logger.error("Aliment with id " + id + " was not found in the database.");
            throw new AlimentNotFoundException(id);
        }
    }
}
