package com.crud.task.service;

import com.crud.task.domain.Mail;
import com.crud.task.repository.TaskRepository;
import com.crud.task.scheduler.EmailScheduler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.EmptyStackException;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SimpleEmailServiceTest {


    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private EmailScheduler emailScheduler;

    @Test
    public void shouldSendEmail() {
        //Given
        Mail mail = Mail.builder()
                .mailTo("test@test.com")
                .message("Test message")
                .subject("Subject Test")
//                .toCc(Optional.empty())
                .build();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        //When
        simpleEmailService.send(mail);
        emailScheduler.sendInformationEmail();
        //Then
      //  Assertions.assertEquals(Optional.empty(),mail.getToCc());
        verify(javaMailSender,times(1)).send(mailMessage);
        verify(emailScheduler,times(1)).sendInformationEmail();

    }
}