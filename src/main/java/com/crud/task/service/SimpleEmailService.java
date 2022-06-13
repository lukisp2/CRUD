package com.crud.task.service;

import com.crud.task.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {
    private final JavaMailSender javaMailSender;

    public void send(final Mail mail) {

        log.info("Starting email preparation");

        try {
            SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.send(mailMessage);
            log.info("Email has been send...");
        } catch (MailException e) {
            log.error("Failed to send mail " + e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        if (mail.getToCc().isPresent()) {
            mailMessage.setCc(mail.getToCc().get());
            log.info("Email CC is: " + mail.getToCc().get());
        }

        return mailMessage;
    }
}
