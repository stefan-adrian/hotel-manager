package fii.hotel.manager.model;

public enum CommandStatus {
    RECEIVED("RECEIVED"),
    PREPARING("PREPARING"),
    PREPARED("PREPARED"),
    DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED");

    private String status;

    CommandStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
