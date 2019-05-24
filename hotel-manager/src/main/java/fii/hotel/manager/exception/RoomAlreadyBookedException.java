package fii.hotel.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Room already booked at that time")
public class RoomAlreadyBookedException extends RuntimeException {

    public RoomAlreadyBookedException(String message) {
        super(message);
    }

    public RoomAlreadyBookedException(LocalDate startTime, LocalDate endTime) {
        this("Room already booked between " + startTime + " and " + endTime);
    }
}
