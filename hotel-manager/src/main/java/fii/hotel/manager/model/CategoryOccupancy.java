package fii.hotel.manager.model;

public enum CategoryOccupancy {
    NINETY_PERCENT("NinetyPercent",0.9,1.5),
    EIGHTY_PERCENT("EightyPercent",0.8,1.4),
    SEVENTY_PERCENT("SeventyPercent",0.7,1.3),
    SIXTY_PERCENT("SixtyPercent",0.6,1.2),
    FIFTY_PERCENT("FiftyPercent",0.5,1.1);

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
