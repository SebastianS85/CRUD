package com.crud.tasks.trello.config;

import com.crud.tasks.config.AdminConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminConfigTestSuite {

    @Autowired
    AdminConfig adminConfig;
    @Test
    public void getAdminMail(){
        //Given
        //When
        String mail=adminConfig.getAdminMail();
        //Then
        assertEquals("smuk.sebastian@gmx.de",mail);
    }

}
