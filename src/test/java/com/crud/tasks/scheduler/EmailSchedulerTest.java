package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {

    @InjectMocks
    private EmailScheduler emailScheduler;
    @Mock
   private TaskRepository taskRepository;
    @Mock
    private SimpleEmailService simpleEmailService;
    @Mock
    private AdminConfig adminConfig;


    @Test
    public void sendInformationEmail() {
        //Given

        when(taskRepository.count()).thenReturn(2L);
        when(adminConfig.getAdminMail()).thenReturn("test@com.pl");

        //When
        emailScheduler.sendInformationEmail();

        verify(simpleEmailService, Mockito.times(1)).send(any());


    }
}