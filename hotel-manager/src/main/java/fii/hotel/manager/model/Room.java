package fii.hotel.manager.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Room {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer floor;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "room")
    private Set<Booking> bookings;

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }
}
