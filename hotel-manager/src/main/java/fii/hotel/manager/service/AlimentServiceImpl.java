package fii.hotel.manager.service;

import fii.hotel.manager.model.Aliment;
import fii.hotel.manager.repository.AlimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlimentServiceImpl implements AlimentService {
    private AlimentRepository alimentRepository;

    @Autowired
    public AlimentServiceImpl(AlimentRepository alimentRepository) {
        this.alimentRepository = alimentRepository;
    }

    @Override
    public Aliment save(Aliment aliment) {
        Aliment alimentSaved = alimentRepository.save(aliment);
        return alimentSaved;
    }
}
