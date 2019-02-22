package fii.hotel.manager.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SpaTypeDto implements Serializable {

    @ApiModelProperty(value = "Id", readOnly = true)
    private Long id;

    private String name;

    private Double price;

    public SpaTypeDto() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
