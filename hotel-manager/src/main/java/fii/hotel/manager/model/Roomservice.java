package fii.hotel.manager.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Roomservice {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime timeOfOrder;

    @ManyToMany
    private List<Aliment> aliments;

    private Double totalCommandPrice;

    private CommandStatus commandStatus;

    @ManyToOne
    private Booking booking;

    public Roomservice() {
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

    public CommandStatus getCommandStatus() {
        return commandStatus;
    }

    public void setCommandStatus(CommandStatus commandStatus) {
        this.commandStatus = commandStatus;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
