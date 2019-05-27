package fii.hotel.manager.service;

import fii.hotel.manager.model.Booking;
import fii.hotel.manager.model.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceImplTest {
    private Room room;
    @InjectMocks
    private RoomServiceImpl roomService;

    @Before
    public void setup(){
        room=new Room();
        Booking booking=new Booking();
        booking.setFromTime(LocalDate.of(2019,06,10));
        booking.setToTime(LocalDate.of(2019,06,20));
        Set<Booking> bookings=new HashSet<>();
        bookings.add(booking);
        room.setId((long) 1);
        room.setBookings(bookings);
    }

    @Test
    public void checkIfBookingTimeAvailable_shouldReturnTrue_whenGivenEndDateIsBookingStartDate(){
        //given
        LocalDate startDate=LocalDate.of(2019,06,01);
        LocalDate endDate=LocalDate.of(2019,06,10);

        //when
        boolean value=roomService.checkIfBookingTimeAvailable(room,startDate,endDate);

        //then
        Assert.assertTrue(value);
    }

    @Test
    public void checkIfBookingTimeAvailable_shouldReturnTrue_whenGivenStartDateIsBookingEndDate(){
        //given
        LocalDate startDate=LocalDate.of(2019,06,20);
        LocalDate endDate=LocalDate.of(2019,06,30);

        //when
        boolean value=roomService.checkIfBookingTimeAvailable(room,startDate,endDate);

        //then
        Assert.assertTrue(value);
    }

    @Test
    public void checkIfBookingTimeAvailable_shouldReturnFalse_whenGivenEndDateIsAfterBookingStartDate(){
        //given
        LocalDate startDate=LocalDate.of(2019,06,05);
        LocalDate endDate=LocalDate.of(2019,06,15);

        //when
        boolean value=roomService.checkIfBookingTimeAvailable(room,startDate,endDate);

        //then
        Assert.assertFalse(value);
    }

    @Test
    public void checkIfBookingTimeAvailable_shouldReturnFalse_whenGivenDatesAreBetweenBookingDates(){
        //given
        LocalDate startDate=LocalDate.of(2019,06,12);
        LocalDate endDate=LocalDate.of(2019,06,15);

        //when
        boolean value=roomService.checkIfBookingTimeAvailable(room,startDate,endDate);

        //then
        Assert.assertFalse(value);
    }

    @Test
    public void checkIfBookingTimeAvailable_shouldReturnFalse_whenGivenStartDateIsBeforeBookingEndDate(){
        //given
        LocalDate startDate=LocalDate.of(2019,06,16);
        LocalDate endDate=LocalDate.of(2019,06,25);

        //when
        boolean value=roomService.checkIfBookingTimeAvailable(room,startDate,endDate);

        //then
        Assert.assertFalse(value);
    }

    @Test
    public void checkIfBookingTimeAvailable_shouldReturnFalse_whenBookingDateIsBetweenGivenDate(){
        //given
        LocalDate startDate=LocalDate.of(2019,06,5);
        LocalDate endDate=LocalDate.of(2019,06,25);

        //when
        boolean value=roomService.checkIfBookingTimeAvailable(room,startDate,endDate);

        //then
        Assert.assertFalse(value);
    }

    @Test
    public void checkIfBookingTimeAvailable_shouldReturnFalse_whenBookingDateIsAsGivenDate(){
        //given
        LocalDate startDate=LocalDate.of(2019,06,10);
        LocalDate endDate=LocalDate.of(2019,06,20);

        //when
        boolean value=roomService.checkIfBookingTimeAvailable(room,startDate,endDate);

        //then
        Assert.assertFalse(value);
    }

    @Test
    public void checkIfBookingTimeAvailable_shouldReturnFalse_whenStartDateIsAfterEndDate(){
        //given
        LocalDate startDate=LocalDate.of(2019,06,25);
        LocalDate endDate=LocalDate.of(2019,06,22);

        //when
        boolean value=roomService.checkIfBookingTimeAvailable(room,startDate,endDate);

        //then
        Assert.assertFalse(value);
    }

    @Test
    public void checkIfBookingTimeAvailable_shouldReturnFalse_whenStartDateIsEqualToEndDate(){
        //given
        LocalDate startDate=LocalDate.of(2019,06,22);
        LocalDate endDate=LocalDate.of(2019,06,22);

        //when
        boolean value=roomService.checkIfBookingTimeAvailable(room,startDate,endDate);

        //then
        Assert.assertFalse(value);
    }
}