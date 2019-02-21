package fii.hotel.manager.service;

import fii.hotel.manager.model.Aliment;

import java.util.List;

public interface AlimentService {

    Aliment save(Aliment aliment);

    List<Aliment> getAll();

}
