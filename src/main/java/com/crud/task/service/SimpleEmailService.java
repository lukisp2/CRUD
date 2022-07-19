package com.crud.task.service;

import com.crud.task.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    public void send(final Mail mail) {

        log.info("Starting email preparation");

        try {
            javaMailSender.send(createMimeMessage(mail));
            // SimpleMailMessage mailMessage = createMailMessage(mail);
            //javaMailSender.send(mailMessage);
            log.info("Email has been send...");
        } catch (MailException e) {
            log.error("Failed to send mail " + e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo("lukaszmarchelelk@gmail.com");

            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()),true);
        };
    }


    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        return mailMessage;
    }
}
