package fii.hotel.manager.service;

import fii.hotel.manager.model.Customer;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender emailSender;

    private Configuration freemarkerConfig;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, Configuration freemarkerConfig) {
        this.emailSender = emailSender;
        this.freemarkerConfig = freemarkerConfig;
    }

    @Override
    public void sendWelcomeMail(Customer customer) {
        try {
            MimeMessage mail = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            Template template = freemarkerConfig.getTemplate("welcome-email-template.ftl");
            Map model = new HashMap();
            model.put("name", customer.getName());
            String htmlMessage = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setTo(customer.getEmail());
            helper.setText(htmlMessage, true);
            helper.setSubject("Welcome to our community!");

            emailSender.send(mail);

        } catch (IOException | TemplateException | MessagingException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void sendSimpleWelcomeMail(Customer customer) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customer.getEmail());
        message.setSubject("Welcome to our community!");
        message.setText("Hello " + customer.getName());
        emailSender.send(message);
    }
}
