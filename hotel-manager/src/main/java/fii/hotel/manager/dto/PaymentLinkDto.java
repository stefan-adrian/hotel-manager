package fii.hotel.manager.dto;

import java.io.Serializable;

public class PaymentLinkDto implements Serializable {

    private String link;

    public PaymentLinkDto() {
    }

    public PaymentLinkDto(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
