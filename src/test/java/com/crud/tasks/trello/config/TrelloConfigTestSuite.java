package com.crud.tasks.trello.config;

import com.crud.tasks.config.TrelloConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloConfigTestSuite {
    @Autowired
    TrelloConfig trelloConfig;

    @Test
    public void testTrelloConfig() {
        //Given
        //When
        String username = trelloConfig.getUsername();
        String endpoint = trelloConfig.getTrelloApiEndpoint();
        String token = trelloConfig.getTrelloToken();
        String key=trelloConfig.getTrelloAppKey();
        //Then
        assertEquals("sebas463", username);
        assertEquals("https://api.trello.com/1", endpoint);
        assertEquals("75c25688b8097bfa1fe248ff6442dacd89a263b2c6f004d40a312aa43837ef75", token);
        assertEquals("8f350cf8c06e1df5cdbfff61ab1bfafb", key);

    }


}
