package fii.hotel.manager.service;

import fii.hotel.manager.model.Category;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public interface PriceService {

    Map<String, Double> getCategoriesPrices(Set<Category> categories,LocalDate arrivalDate,LocalDate departureDate);

}
