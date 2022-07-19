package com.crud.task.service;

import com.crud.task.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message" , message);
        context.setVariable("task_url", "https://lukisp2.github.io");
        context.setVariable("button","Visit website");
        context.setVariable("admin_name",adminConfig.getAdminName());
        return templateEngine.process("mail/created-trello-card-mail",context);
    }
}
