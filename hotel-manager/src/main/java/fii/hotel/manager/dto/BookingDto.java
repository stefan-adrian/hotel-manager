package fii.hotel.manager.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class BookingDto implements Serializable {

    private Long id;

    private CustomerDto customerDto;

    private RoomDto roomDto;

    private LocalDateTime fromTime;

    private LocalDateTime toTime;

    private Boolean roomCleaning;

    private List<CarOrderDto> carOrderDtos;

    private List<SpaEventDto> spaEventDtos;

    public BookingDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }

    public RoomDto getRoomDto() {
        return roomDto;
    }

    public void setRoomDto(RoomDto roomDto) {
        this.roomDto = roomDto;
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

    public List<CarOrderDto> getCarOrderDtos() {
        return carOrderDtos;
    }

    public void setCarOrderDtos(List<CarOrderDto> carOrderDtos) {
        this.carOrderDtos = carOrderDtos;
    }

    public List<SpaEventDto> getSpaEventDtos() {
        return spaEventDtos;
    }

    public void setSpaEventDtos(List<SpaEventDto> spaEventDtos) {
        this.spaEventDtos = spaEventDtos;
    }
}
