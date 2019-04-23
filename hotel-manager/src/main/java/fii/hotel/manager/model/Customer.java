package fii.hotel.manager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String password;

    private String name;

    private String surname;

    private Role role;

    @OneToMany(mappedBy = "customer")
    private Set<Booking> bookings;

    public Customer() {
    }

    public Customer(Customer customer) {
        this.id = customer.getId();
        this.email = customer.getEmail();
        this.password = customer.getPassword();
        this.name = customer.getName();
        this.surname = customer.getSurname();
        this.role = customer.getRole();
        this.bookings = customer.getBookings();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }
}
