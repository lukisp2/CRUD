package com.crud.task.scheduler;

import com.crud.task.config.AdminConfig;
import com.crud.task.domain.Mail;
import com.crud.task.repository.TaskRepository;
import com.crud.task.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RequiredArgsConstructor
@Component
public class EmailScheduler {
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "Tasks: Once a day email";
    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;

    //@Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String message = "Currently in database you got: " + size + (size == 1 ? " task" : " tasks");
        simpleEmailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        message
                )
        );
    }


    @Scheduled(cron = "30 * * * * *")
    public void sendDbInformationMail() {
        Context context = new Context();
        long size = taskRepository.count();
        String message = "You have " + size + " tasks in your database!";
        context.setVariable("message", message);
        context.setVariable("task_url", "https://lukisp2.github.io");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        templateEngine.process("mail/database-mail", context);
    }

}
