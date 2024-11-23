package com.mailservice.serviceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mailservice.service.MailService;

@Service
public class MailServiceImpl implements MailService {

    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }
}
