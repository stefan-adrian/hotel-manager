package fii.hotel.manager.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
