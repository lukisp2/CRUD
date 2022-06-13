package com.crud.task.scheduler;

import com.crud.task.config.AdminConfig;
import com.crud.task.domain.Mail;
import com.crud.task.repository.TaskRepository;
import com.crud.task.service.SimpleEmailService;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.IMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailScheduler {
    private static final String SUBJECT = "Tasks: Once a day email";
    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String message ="";
        if (size == 1) {
            message = "Currently in database you got: " + size + " task";
        } else {
            message = "Currently in database you got: " + size + " tasks";
        }
        simpleEmailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        message
                )
        );
    }
}
