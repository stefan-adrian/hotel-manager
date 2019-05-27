package fii.hotel.manager.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Booking {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Room room;

    private LocalDate fromTime;

    private LocalDate toTime;

    private Boolean roomCleaning;

    private Double price;

    @OneToMany(mappedBy = "booking")
    private Set<Roomservice> roomservices;

    @OneToMany(mappedBy = "booking")
    private Set<CarOrder> carOrders;

    @OneToMany(mappedBy = "booking")
    private Set<SpaEvent> spaEvents;

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalDate fromTime) {
        this.fromTime = fromTime;
    }

    public LocalDate getToTime() {
        return toTime;
    }

    public void setToTime(LocalDate toTime) {
        this.toTime = toTime;
    }

    public Boolean getRoomCleaning() {
        return roomCleaning;
    }

    public void setRoomCleaning(Boolean roomCleaning) {
        this.roomCleaning = roomCleaning;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Roomservice> getRoomservices() {
        return roomservices;
    }

    public void setRoomservices(Set<Roomservice> roomservices) {
        this.roomservices = roomservices;
    }

    public Set<CarOrder> getCarOrders() {
        return carOrders;
    }

    public void setCarOrders(Set<CarOrder> carOrders) {
        this.carOrders = carOrders;
    }

    public Set<SpaEvent> getSpaEvents() {
        return spaEvents;
    }

    public void setSpaEvents(Set<SpaEvent> spaEvents) {
        this.spaEvents = spaEvents;
    }
}
