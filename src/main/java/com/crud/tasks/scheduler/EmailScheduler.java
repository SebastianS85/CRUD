package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "Tasks once a day Email";

    private String response;

    //@Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        if (size == 1) {
            response = "task";
        } else {
            response = "tasks";
        }

        simpleEmailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "currently id Database you got " + size + " " + response, null));


    }
}
