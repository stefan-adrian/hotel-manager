package fii.hotel.manager.model;

public enum NumberOfDaysDiscounts {
    NINETY(90,0.3),
    SIXTY(60,0.2),
    THIRTY(30,0.1);

    private Integer numberOfDays;
    private Double discountPercentage;

    NumberOfDaysDiscounts(Integer numberOfDays, Double discountPercentage) {
        this.numberOfDays = numberOfDays;
        this.discountPercentage = discountPercentage;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }
}
