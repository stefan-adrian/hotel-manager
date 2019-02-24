package fii.hotel.manager.model;

import javax.persistence.*;
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

    private LocalDateTime fromTime;

    private LocalDateTime toTime;

    private Boolean roomCleaning;

    @OneToMany
    private Set<RoomService> roomServices;

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

    public Set<RoomService> getRoomServices() {
        return roomServices;
    }

    public void setRoomServices(Set<RoomService> roomServices) {
        this.roomServices = roomServices;
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
