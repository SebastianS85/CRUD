package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TaskRepository taskRepository;

    public String buildTrelloCardMail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("you can manage your tasks");
        functionality.add("provides connection with trello account");
        functionality.add("application alows sending tasks to trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http//localhost:8888/crud");
        context.setVariable("button", "Visite website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye", "see you  " + adminConfig.getAdminName());
        context.setVariable("companyDetails", adminConfig.getAdminCompany());
        context.setVariable("is_friend", true);
        context.setVariable("show_button", false);
        context.setVariable("application_functionality", functionality);

//        return templateEngine.process("mail/created-trello-card-mail", context);
        return templateEngine.process("mail/once-a-day-email", context);
    }

    public String buildOnceADayMAil(String message) {

        Context context = new Context();
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("task_quantity", taskRepository.count());
        return templateEngine.process("mail/once-a-day-email", context);
    }
}
