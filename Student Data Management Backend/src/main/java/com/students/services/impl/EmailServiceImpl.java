package com.students.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendVerificationMail(String email,String subject,String text) throws MessagingException {
        try {

            MimeMessage message=javaMailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(message,"utf-8");

            helper.setSubject(subject);
            helper.setText(text);
            helper.setTo(email);

            javaMailSender.send(message);

        } catch (MessagingException e) {
            throw new MessagingException("Failed to send email");
        }
    }
}
