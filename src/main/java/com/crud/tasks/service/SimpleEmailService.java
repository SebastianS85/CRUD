package com.crud.tasks.service;


import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;


    public void send(final Mail mail) {

        LOGGER.info("starting email Service");
        try {
            SimpleMailMessage mailMessage=createMailMessege(mail);
            javaMailSender.send(mailMessage);
            LOGGER.info("email has been send");

        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMailMessege(final Mail mail) {

        SimpleMailMessage mailMessege = new SimpleMailMessage();
        mailMessege.setTo(mail.getMailTo());
        if(mail.getToCC()!=null){
            mailMessege.setCc(mail.getToCC());
        }
        mailMessege.setCc(mail.getToCC());
        mailMessege.setSubject(mail.getSubject());
        mailMessege.setText(mail.getMessage());
        LOGGER.info("email has been send");

        return mailMessege;
    }
}