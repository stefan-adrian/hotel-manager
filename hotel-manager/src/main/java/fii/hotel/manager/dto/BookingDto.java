package fii.hotel.manager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class BookingDto implements Serializable {

    private Long id;

    @JsonProperty("customer")
    private CustomerDto customerDto;

    @JsonProperty("room")
    private RoomDto roomDto;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fromTime;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate toTime;

    private Boolean roomCleaning;

    private Double price;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime bookingTime;

    private List<CarOrderDto> carOrderDtos;

    private List<SpaEventDto> spaEventDtos;

    private List<RoomserviceDto> roomserviceDtos;

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

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
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

    public List<RoomserviceDto> getRoomserviceDtos() {
        return roomserviceDtos;
    }

    public void setRoomserviceDtos(List<RoomserviceDto> roomserviceDtos) {
        this.roomserviceDtos = roomserviceDtos;
    }
}
