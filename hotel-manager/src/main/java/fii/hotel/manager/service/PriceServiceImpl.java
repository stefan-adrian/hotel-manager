package fii.hotel.manager.service;

import fii.hotel.manager.model.Category;
import fii.hotel.manager.model.CategoryOccupancy;
import fii.hotel.manager.model.DateRates;
import fii.hotel.manager.model.NumberOfDaysDiscounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class PriceServiceImpl implements  PriceService{

    private Map<String,Double> categoriesPrices=new HashMap<>();
    private RoomService roomService;

    @Autowired
    public PriceServiceImpl(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public Map<String, Double> getCategoriesPrices(Set<Category> categories,LocalDate arrivalDate, LocalDate departureDate){
        calculateCategoriesPrices(categories,arrivalDate,departureDate);
        return categoriesPrices;
    }

    private void calculateCategoriesPrices(Set<Category> categories, LocalDate arrivalDate, LocalDate departureDate) {
        categories.forEach(category ->
            categoriesPrices.put(category.getName(),calculateCategoryTotalBookingPrice(category,arrivalDate,departureDate))
        );
    }

    private Double calculateCategoryTotalBookingPrice(Category category,LocalDate arrivalDate,LocalDate departureDate){
        Double totalPrice=0.0;
        for(LocalDate date=arrivalDate;date.isBefore(departureDate);date=date.plusDays(1)){
            totalPrice+=getBookingPriceForDayByCategory(category,date);
        }
        totalPrice*= getPriceRemainingPercentageByNumberOfBookingDays(arrivalDate,departureDate);
        return totalPrice;
    }

    private Double getBookingPriceForDayByCategory(Category category,LocalDate date){
        Double totalDayPrice=category.getPrice();
        totalDayPrice+=getExtraPriceByRoomsOccupancy(category,date);
        totalDayPrice+=getExtraPriceBySpecialDates(category,date);
        return totalDayPrice;
    }

    private Double getExtraPriceByRoomsOccupancy(Category category,LocalDate date){
        Integer totalRooms=category.getRooms().size();
        Integer availableRooms=roomService.getNumberOfAvailableRoomsBetweenDates(category.getRooms(),date,date.plusDays(1));
        Double occupancyPercentage = Double.valueOf(totalRooms-availableRooms)/totalRooms;
        for(CategoryOccupancy categoryOccupancy:CategoryOccupancy.values()){
            if(occupancyPercentage>=categoryOccupancy.getPercent()){
                return category.getPrice()*categoryOccupancy.getMultiplyValue();
            }
        }
        if(occupancyPercentage<=0.1&&DAYS.between(LocalDate.now(), date)<=1){
            return category.getPrice()*(-0.1);
        }
        return 0.0;

    }

    private Double getExtraPriceBySpecialDates(Category category,LocalDate date){
        for(DateRates dateRates:DateRates.values()){
            if(date.getMonthValue()==dateRates.getMonth()&&date.getDayOfMonth()==dateRates.getDay()){
                return category.getPrice()*dateRates.getMultiplyValue();
            }
        }
        return 0.0;
    }

    private Double getPriceRemainingPercentageByNumberOfBookingDays(LocalDate arrivalDate, LocalDate departureDate){
        long bookingDays=DAYS.between(arrivalDate,departureDate);
        for(NumberOfDaysDiscounts numberOfDaysDiscounts:NumberOfDaysDiscounts.values()){
            if(bookingDays>=numberOfDaysDiscounts.getNumberOfDays()){
                return 1.0-numberOfDaysDiscounts.getDiscountPercentage();
            }
        }
        return 1.0;
    }

}
