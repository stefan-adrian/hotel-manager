package fii.hotel.manager.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class CategoryDto implements Serializable {

    @ApiModelProperty(value = "Id", readOnly = true)
    private Long id;

    private String name;

    private Integer beds;

    private Integer occupancy;

    private Boolean safe;

    private Boolean coffeeMaker;

    private Boolean kitchen;

    private Double price;

    private byte[] image;

    private Integer size;

    private String description;

    public CategoryDto() {
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

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Integer getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(Integer occupancy) {
        this.occupancy = occupancy;
    }

    public Boolean getSafe() {
        return safe;
    }

    public void setSafe(Boolean safe) {
        this.safe = safe;
    }

    public Boolean getCoffeeMaker() {
        return coffeeMaker;
    }

    public void setCoffeeMaker(Boolean coffeeMaker) {
        this.coffeeMaker = coffeeMaker;
    }

    public Boolean getKitchen() {
        return kitchen;
    }

    public void setKitchen(Boolean kitchen) {
        this.kitchen = kitchen;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
