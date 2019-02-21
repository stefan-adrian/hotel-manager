package fii.hotel.manager.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BookingCreationDto implements Serializable {

    private Long roomId;

    private LocalDateTime fromTime;

    private LocalDateTime toTime;

    private Boolean roomCleaning;

    public BookingCreationDto() {
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalDateTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalDateTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalDateTime toTime) {
        this.toTime = toTime;
    }

    public Boolean getRoomCleaning() {
        return roomCleaning;
    }

    public void setRoomCleaning(Boolean roomCleaning) {
        this.roomCleaning = roomCleaning;
    }
}
