package fii.hotel.manager.model;

public enum DateRates {
    NEW_YEAR_DAY(1,1,0.3),
    UNION_DAY(1,24,0.1),
    VALENTINES_DAY(2,14,0.4),
    SPRING_DAY(3,1,0.1),
    WOMEN_DAY(3,8,0.5),
    WORK_DAY(5,1,0.2),
    CHILD_DAY(6,1,0.1),
    SAINT_ANDREI_DAY(11,30,0.1),
    ROMANIAN_NATIONAL_DAY(12,1,0.1),
    CHRISTMAS_EVE(12,24,0.3),
    CHRISTMAS_FIRST_DAY(12,25,0.3),
    CHRISTMAS_SECOND_DAY(12,26,0.1),
    NEW_YEAR_EVE(12,31,1.0);

    private Integer month;
    private Integer day;
    private Double multiplyValue;

    DateRates(Integer month, Integer day, Double multiplyValue) {
        this.month = month;
        this.day = day;
        this.multiplyValue = multiplyValue;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getDay() {
        return day;
    }

    public Double getMultiplyValue() {
        return multiplyValue;
    }
}
