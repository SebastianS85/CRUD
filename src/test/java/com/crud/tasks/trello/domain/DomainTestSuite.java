package com.crud.tasks.trello.domain;


import com.crud.tasks.domain.AttachmentsByType;
import com.crud.tasks.domain.Badges;
import com.crud.tasks.domain.Trello;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DomainTestSuite {

    @Test
    public void domainTest(){
        //Given
        Trello trello=new Trello(1111,222);
        AttachmentsByType attachmentsByType=new AttachmentsByType(trello);
        //When
        Badges badges=new Badges(2,attachmentsByType);
        //Then

        assertEquals(1111,badges.getAttachmentsByType().getTrello().getBoard());
        assertEquals(222,badges.getAttachmentsByType().getTrello().getCard());
        assertEquals(2,badges.getVote());
        assertEquals(trello,badges.getAttachmentsByType().getTrello());

    }



}
