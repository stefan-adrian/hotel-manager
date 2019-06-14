package fii.hotel.manager.dto;

import java.io.Serializable;

public class CategoryBookingDto implements Serializable {
    private String name;
    private Integer beds;
    private Double categoryBasicPrice;
    private Double totalBookingPrice;
    private Integer availableRooms;
    private Integer totalRooms;
    private byte[] image;
    private Integer size;
    private String description;

    public CategoryBookingDto() {
        availableRooms=1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Double getCategoryBasicPrice() {
        return categoryBasicPrice;
    }

    public void setCategoryBasicPrice(Double categoryBasicPrice) {
        this.categoryBasicPrice = categoryBasicPrice;
    }

    public Double getTotalBookingPrice() {
        return totalBookingPrice;
    }

    public void setTotalBookingPrice(Double totalBookingPrice) {
        this.totalBookingPrice = totalBookingPrice;
    }

    public Integer getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(Integer availableRooms) {
        this.availableRooms = availableRooms;
    }

    public Integer getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(Integer totalRooms) {
        this.totalRooms = totalRooms;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
