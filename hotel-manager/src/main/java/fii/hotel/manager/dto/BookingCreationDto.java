package fii.hotel.manager.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BookingCreationDto implements Serializable {

    @ApiModelProperty(value = "Id", readOnly = true)
    private Long id;

    private Long roomId;

    @ApiModelProperty(value = "Customer Id", readOnly = true)
    private Long customerId;

    private LocalDateTime fromTime;

    private LocalDateTime toTime;

    private Boolean roomCleaning;

    public BookingCreationDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
