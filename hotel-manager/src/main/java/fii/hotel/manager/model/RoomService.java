package fii.hotel.manager.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class RoomService {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime timeOfOrder;

    private List<Aliment> aliments;

    private Double totalCommandPrice;

    public RoomService() {
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

    public List<Aliment> getAliments() {
        return aliments;
    }

    public void setAliments(List<Aliment> aliments) {
        this.aliments = aliments;
    }

    public Double getTotalCommandPrice() {
        return totalCommandPrice;
    }

    public void setTotalCommandPrice(Double totalCommandPrice) {
        this.totalCommandPrice = totalCommandPrice;
    }
}
