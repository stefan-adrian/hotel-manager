package fii.hotel.manager.model;

public enum CategoryOccupancy {
    NINETY_PERCENT("NinetyPercent", 0.9, 0.25),
    EIGHTY_PERCENT("EightyPercent", 0.8, 0.2),
    SEVENTY_PERCENT("SeventyPercent", 0.7, 0.15),
    SIXTY_PERCENT("SixtyPercent", 0.6, 0.1),
    FIFTY_PERCENT("FiftyPercent", 0.5, 0.05);

    private String label;
    private Double percent;
    private Double multiplyValue;

    CategoryOccupancy(String label, Double percent, Double multiplyValue) {
        this.label = label;
        this.percent = percent;
        this.multiplyValue = multiplyValue;
    }

    public String getLabel() {
        return label;
    }

    public Double getPercent() {
        return percent;
    }

    public Double getMultiplyValue() {
        return multiplyValue;
    }
}
