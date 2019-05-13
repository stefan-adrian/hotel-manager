package fii.hotel.manager.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class RoomDto implements Serializable {

    @ApiModelProperty(value = "Id", readOnly = true)
    private Long id;

    private String name;

    private Integer floor;

    private CategoryDto categoryDto;

    public RoomDto() {
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

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }
}
