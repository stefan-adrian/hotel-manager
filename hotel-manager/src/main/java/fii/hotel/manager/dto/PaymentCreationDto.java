package fii.hotel.manager.dto;

import java.io.Serializable;

public class PaymentCreationDto implements Serializable {
    private String itemName;

    private Double amount;

    public PaymentCreationDto() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentCreationDto{" +
                "itemName='" + itemName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
