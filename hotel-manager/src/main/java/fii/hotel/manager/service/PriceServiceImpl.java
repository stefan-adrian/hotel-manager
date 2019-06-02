package fii.hotel.manager.service;

import fii.hotel.manager.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class PriceServiceImpl implements  PriceService{

    private Map<String,Double> categoriesPrices=new HashMap<>();
    private RoomService roomService;
    private BookingService bookingService;

    @Autowired
    public PriceServiceImpl(RoomService roomService, BookingService bookingService) {
        this.roomService = roomService;
        this.bookingService = bookingService;
    }

    @Override
    public Map<String, Double> getCategoriesPrices(Set<Category> categories,LocalDate arrivalDate, LocalDate departureDate,String email){
        calculateCategoriesPrices(categories,arrivalDate,departureDate,email);
        return categoriesPrices;
    }

    private void calculateCategoriesPrices(Set<Category> categories, LocalDate arrivalDate, LocalDate departureDate,String email) {
        categories.forEach(category ->
            categoriesPrices.put(category.getName(),calculateCategoryTotalBookingPrice(category,arrivalDate,departureDate,email))
        );
    }

    private Double calculateCategoryTotalBookingPrice(Category category,LocalDate arrivalDate,LocalDate departureDate,String email){
        Double totalPrice=0.0;
        for(LocalDate date=arrivalDate;date.isBefore(departureDate);date=date.plusDays(1)){
            totalPrice+=getBookingPriceForDayByCategory(category,date);
        }
        totalPrice*= getPriceRemainingPercentageByNumberOfBookingDays(arrivalDate,departureDate);
        totalPrice*=getPriceIncreasePercentageByNumberOfBookingInLastDay();
        totalPrice*=getPriceDiscountPercentageByCustomerPreviousBookings(email);
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

    private Double getPriceIncreasePercentageByNumberOfBookingInLastDay(){
        Integer numberOfBookings=bookingService.getNumberOfBookingsIn24HoursIntervalBeforeNow();
        if(numberOfBookings>=100){
            return 1.1;
        }
        return 1.0;
    }

    private Double getPriceDiscountPercentageByCustomerPreviousBookings(String email){
        List<Booking> bookings=bookingService.getBookingsByCustomerEmail(email);
        Integer numberOfBookings=bookings.size();
        Double totalBookingsPrice=bookings.stream().mapToDouble(Booking::getPrice).sum();
        if(totalBookingsPrice>=50000.0){
            return 0.7;
        }
        if(numberOfBookings>=10){
            return 0.8;
        }
        if(numberOfBookings>=5){
            return 0.9;
        }
        if(numberOfBookings>=2){
            return 0.95;
        }
        return 1.0;
    }
}
