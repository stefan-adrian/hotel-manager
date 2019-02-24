package fii.hotel.manager.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SpaEventDto implements Serializable {
    @ApiModelProperty(value = "Id", readOnly = true)
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private SpaTypeDto spaTypeDto;

    @ApiModelProperty(value = "Booking Id", readOnly = true)
    private Long bookingId;

    public SpaEventDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public SpaTypeDto getSpaTypeDto() {
        return spaTypeDto;
    }

    public void setSpaTypeDto(SpaTypeDto spaTypeDto) {
        this.spaTypeDto = spaTypeDto;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}
