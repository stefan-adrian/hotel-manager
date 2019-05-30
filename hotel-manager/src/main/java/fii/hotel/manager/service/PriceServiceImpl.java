package fii.hotel.manager.service;

import fii.hotel.manager.dto.CategoryBookingDto;
import fii.hotel.manager.model.CategoryOccupancy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceServiceImpl implements  PriceService{

    private Map<String,Double> categoriesPrices=new HashMap<>();

    public PriceServiceImpl() {
    }

    @Override
    public Map<String, Double> getCategoriesPricesBetweenDates(List<CategoryBookingDto> categoryBookingDtos, LocalDate arrivalDate, LocalDate departureDate) {
        categoryBookingDtos.forEach(categoryBookingDto -> categoriesPrices.put(categoryBookingDto.getName(),
                                                            calculateCategoryPrice(categoryBookingDto,arrivalDate,departureDate)));
        return null;
    }

    private Double calculateCategoryPrice(CategoryBookingDto categoryBookingDto,LocalDate arrivalDate,LocalDate departureDate){
        Double priceByOccupancy=getPriceByRoomsOccupancy(categoryBookingDto);
        return priceByOccupancy;
    }

    public Double getPriceByRoomsOccupancy(CategoryBookingDto categoryBookingDto){
        Integer totalRooms=categoryBookingDto.getTotalRooms();
        Integer availableRooms=categoryBookingDto.getAvailableRooms();
        Double occupancyPercentage = Double.valueOf(totalRooms-availableRooms)/totalRooms;
        for(CategoryOccupancy categoryOccupancy:CategoryOccupancy.values()){
            if(occupancyPercentage>=categoryOccupancy.getPercent()){
                return categoryBookingDto.getCategoryBasicPrice()*categoryOccupancy.getMultiplyValue();
            }
        }
        if(occupancyPercentage<=0.1){
            return categoryBookingDto.getCategoryBasicPrice()*0.9;
        }
        return categoryBookingDto.getCategoryBasicPrice();

    }
}
