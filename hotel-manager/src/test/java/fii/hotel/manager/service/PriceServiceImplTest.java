package fii.hotel.manager.service;

import fii.hotel.manager.dto.CategoryBookingDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PriceServiceImplTest {
    private PriceService priceService=new PriceServiceImpl();
    private CategoryBookingDto categoryBookingDto;


    @Before
    public void setup() {
    }

    @Test
    public void getPriceByRoomsOccupancy(){
        //given
        categoryBookingDto = new CategoryBookingDto();
        categoryBookingDto.setAvailableRooms(2);
        categoryBookingDto.setTotalRooms(4);
        categoryBookingDto.setCategoryBasicPrice(1.0);

        //when
        Double returnPrice=priceService.getPriceByRoomsOccupancy(categoryBookingDto);

        //then
        Assert.assertEquals(Double.valueOf(1.1),returnPrice);
    }
}