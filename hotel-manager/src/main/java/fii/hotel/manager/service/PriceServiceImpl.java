package fii.hotel.manager.service;

import fii.hotel.manager.dto.CategoryBookingDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceServiceImpl implements  PriceService{

    private Map<String,Double> categoriesPrices=new HashMap<>();


    @Override
    public Map<String, Double> getCategoriesPricesBetweenDates(List<CategoryBookingDto> categoryBookingDtos, LocalDate arrivalDate, LocalDate departureDate) {
        return null;
    }
}
