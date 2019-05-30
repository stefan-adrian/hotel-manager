package fii.hotel.manager.service;

import fii.hotel.manager.dto.CategoryBookingDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface PriceService {

    Map<String,Double> getCategoriesPricesBetweenDates(List<CategoryBookingDto> categoryBookingDtos, LocalDate arrivalDate, LocalDate departureDate);
}
