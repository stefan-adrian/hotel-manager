package fii.hotel.manager.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SpaEvent {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String location;

    @OneToOne
    @JoinColumn(name = "spa_type_id")
    private SpaType spaType;

    public SpaEvent() {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public SpaType getSpaType() {
        return spaType;
    }

    public void setSpaType(SpaType spaType) {
        this.spaType = spaType;
    }
}
