package fii.hotel.manager.service;

import fii.hotel.manager.model.Category;
import fii.hotel.manager.model.CategoryOccupancy;
import fii.hotel.manager.model.DateRates;
import fii.hotel.manager.model.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class PriceServiceImplTest {
    private Set<Category> categories;
    private Category category1;
    private Set<Room> rooms1;
    @Mock
    private RoomServiceImpl roomService;
    @InjectMocks
    private PriceServiceImpl priceService;


    @Before
    public void setup() {
        categories=new HashSet<>();
        category1 = new Category();
        category1.setName("Category1");
        category1.setPrice(100.0);
        rooms1 = new HashSet<>();
        rooms1.add(new Room());
        rooms1.add(new Room());
        rooms1.add(new Room());
        rooms1.add(new Room());
        category1.setRooms(rooms1);

        categories.add(category1);
    }

    @Test
    public void getCategoriesPrices_whenOccupancyPercentageIs50PercentAnd75() {
        //given
        LocalDate arrivalDate = LocalDate.of(2019, 7, 1);
        LocalDate departureDate = LocalDate.of(2019, 7, 3);
        //when
        Mockito.when(roomService.getNumberOfAvailableRoomsBetweenDates(rooms1, arrivalDate, arrivalDate.plusDays(1))).thenReturn(2);
        Mockito.when(roomService.getNumberOfAvailableRoomsBetweenDates(rooms1, arrivalDate.plusDays(1), departureDate)).thenReturn(1);
        Map<String,Double> returnValue=priceService.getCategoriesPrices(categories,arrivalDate,departureDate);
        //then
        Map<String,Double> expectedMap=new HashMap<>();
        Double fiftyPercentage=CategoryOccupancy.valueOf("FIFTY_PERCENT").getMultiplyValue();
        Double seventyPercentage=CategoryOccupancy.valueOf("SEVENTY_PERCENT").getMultiplyValue();
        Double categoryPrice=category1.getPrice();
        expectedMap.put(category1.getName(),categoryPrice*2+fiftyPercentage*categoryPrice+seventyPercentage*categoryPrice);
        Assert.assertEquals(expectedMap,returnValue);
    }

    @Test
    public void getCategoriesPrices_whenOccupancyPercentageIs50PercentAnd75AndOneDayIsChristmasEve() {
        //given
        LocalDate arrivalDate = LocalDate.of(2019, 12, 23);
        LocalDate departureDate = LocalDate.of(2019, 12, 25);
        //when
        Mockito.when(roomService.getNumberOfAvailableRoomsBetweenDates(rooms1, arrivalDate, arrivalDate.plusDays(1))).thenReturn(2);
        Mockito.when(roomService.getNumberOfAvailableRoomsBetweenDates(rooms1, arrivalDate.plusDays(1), departureDate)).thenReturn(1);
        Map<String,Double> returnValue=priceService.getCategoriesPrices(categories,arrivalDate,departureDate);
        //then
        Map<String,Double> expectedMap=new HashMap<>();
        Double fiftyPercentage=CategoryOccupancy.valueOf("FIFTY_PERCENT").getMultiplyValue();
        Double seventyPercentage=CategoryOccupancy.valueOf("SEVENTY_PERCENT").getMultiplyValue();
        Double christmasEvePercentage= DateRates.valueOf("CHRISTMAS_EVE").getMultiplyValue();
        Double categoryPrice=category1.getPrice();
        expectedMap.put(category1.getName(),categoryPrice*2+fiftyPercentage*categoryPrice
                +seventyPercentage*categoryPrice+christmasEvePercentage*categoryPrice);
        Assert.assertEquals(expectedMap,returnValue);
    }
}