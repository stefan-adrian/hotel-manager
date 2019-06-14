package fii.hotel.manager.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailSender implements Runnable {

    private SimpleMailMessage message;
    private JavaMailSender javaMailSender;

    public EmailSender(SimpleMailMessage message, JavaMailSender javaMailSender) {
        this.message = message;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void run() {
        javaMailSender.send(message);
    }
}
