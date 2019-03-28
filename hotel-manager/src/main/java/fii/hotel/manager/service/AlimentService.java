package fii.hotel.manager.service;

import fii.hotel.manager.model.Aliment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AlimentService {

    Aliment save(Aliment aliment);

    Aliment save(Aliment aliment, MultipartFile image);

    List<Aliment> getAll();

    Aliment getById(Long id);
}
