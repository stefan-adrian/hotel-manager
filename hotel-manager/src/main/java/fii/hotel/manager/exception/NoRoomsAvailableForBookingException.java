package fii.hotel.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No rooms available for booking")
public class NoRoomsAvailableForBookingException extends RuntimeException {
    public NoRoomsAvailableForBookingException(String message) {
        super(message);
    }

    public NoRoomsAvailableForBookingException(LocalDate arrivalDate,LocalDate departureDate) {
        this("No rooms available to book between "+arrivalDate+" and "+departureDate+".");
    }
}
