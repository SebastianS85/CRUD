package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    TrelloService trelloService;
    @Mock
    TrelloClient trelloClient;
    @Mock
    private AdminConfig adminConfig;
    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    public void fetchEmptyTrelloBoards() {
        //Given
        List<TrelloBoardDto> boardDtos=new ArrayList<>();
        when(trelloClient.getTrelloBoards()).thenReturn(boardDtos);
        //When
        List<TrelloBoardDto> newList=trelloService.fetchTrelloBoards();
        //Then
        assertEquals(0,newList.size());

    }
    @Test
    public void fetchTrelloBoards() {
        //Given
        TrelloBoardDto trelloBoardDto=new TrelloBoardDto("2","test",null);
        List<TrelloBoardDto> boardDtos=new ArrayList<>();
        boardDtos.add(trelloBoardDto);
        when(trelloClient.getTrelloBoards()).thenReturn(boardDtos);
        //When
        List<TrelloBoardDto> newList=trelloService.fetchTrelloBoards();
        //Then
        assertEquals(1,newList.size());

    }
    @Test
    public void shouldCreateNewCard(){
        //Given
        TrelloCardDto trelloCardDto=new TrelloCardDto("1","test desc","top","test id");
        CreatedTrelloCardDto newCard=new CreatedTrelloCardDto("1",new Badges(1,new AttachmentsByType(new Trello(1,1))),"test","www.test.com");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(newCard);
        when(adminConfig.getAdminMail()).thenReturn("test@com.pl");
        //When
        CreatedTrelloCardDto theCard= trelloService.createdTrelloCard(trelloCardDto);
        //Then
        assertEquals("1",theCard.getId());
        assertEquals("test",theCard.getName());
        assertEquals(1,theCard.getBadges().getVote());
        assertEquals("www.test.com",theCard.getShortUrl());

    }

}