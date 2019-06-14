package fii.hotel.manager.service;

import fii.hotel.manager.dto.CustomerDto;
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

    private JavaMailSender javaMailSender;

    private Configuration freemarkerConfig;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender, Configuration freemarkerConfig) {
        this.javaMailSender = javaMailSender;
        this.freemarkerConfig = freemarkerConfig;
    }

    @Override
    public void sendWelcomeMail(Customer customer) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
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

            javaMailSender.send(mail);

        } catch (IOException | TemplateException | MessagingException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void sendSimpleWelcomeMail(CustomerDto customerDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customerDto.getEmail());
        message.setSubject("Welcome to our community!");
        String stringMessage="Hello " + customerDto.getName()+",\n\n";
        stringMessage+="Thank you for registering to our site!";
        message.setText(stringMessage);
        createNewThreadAndSendMail(message);
    }

    private void createNewThreadAndSendMail(SimpleMailMessage simpleMailMessage){
        EmailSender emailSender=new EmailSender(simpleMailMessage,javaMailSender);
        new Thread(emailSender).start();
    }
}
