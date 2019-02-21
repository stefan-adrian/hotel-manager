package fii.hotel.manager.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CarOrderDto implements Serializable {
    @ApiModelProperty(value = "Id", readOnly = true)
    private Long id;

    private LocalDateTime toTime;

    @ApiModelProperty(value = "Booking Id", readOnly = true)
    private Long bookingId;

    public CarOrderDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalDateTime toTime) {
        this.toTime = toTime;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}
