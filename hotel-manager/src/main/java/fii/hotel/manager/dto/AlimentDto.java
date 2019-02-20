package fii.hotel.manager.dto;

import java.io.Serializable;

public class AlimentDto implements Serializable {

    private Long Id;

    private String name;

    private Double price;

    public AlimentDto() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
