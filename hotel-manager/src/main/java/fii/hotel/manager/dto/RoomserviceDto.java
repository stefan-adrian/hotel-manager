package fii.hotel.manager.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class RoomserviceDto implements Serializable {
    private Long id;

    private LocalDateTime timeOfOrder;

    private List<AlimentDto> alimentDtos;

    private Double totalCommandPrice;

    private Long bookingId;

    public RoomserviceDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimeOfOrder() {
        return timeOfOrder;
    }

    public void setTimeOfOrder(LocalDateTime timeOfOrder) {
        this.timeOfOrder = timeOfOrder;
    }

    public List<AlimentDto> getAlimentDtos() {
        return alimentDtos;
    }

    public void setAlimentDtos(List<AlimentDto> alimentDtos) {
        this.alimentDtos = alimentDtos;
    }

    public Double getTotalCommandPrice() {
        return totalCommandPrice;
    }

    public void setTotalCommandPrice(Double totalCommandPrice) {
        this.totalCommandPrice = totalCommandPrice;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}