package com.crud.tasks.service;

import com.crud.tasks.domain.TrelloBoardDto;
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

    @Test
    public void fetchEmptyTrelloBoards() {
        //GIVEN
        List<TrelloBoardDto> boardDtos=new ArrayList<>();
        when(trelloClient.getTrelloBoards()).thenReturn(boardDtos);
        //WHEN
        List<TrelloBoardDto> newList=trelloService.fetchTrelloBoards();
        //THen
        assertEquals(0,newList.size());

    }@Test
    public void fetchTrelloBoards() {
        //GIVEN
        TrelloBoardDto trelloBoardDto=new TrelloBoardDto("2","test",null);
        List<TrelloBoardDto> boardDtos=new ArrayList<>();
        boardDtos.add(trelloBoardDto);
        when(trelloClient.getTrelloBoards()).thenReturn(boardDtos);
        //WHEN
        List<TrelloBoardDto> newList=trelloService.fetchTrelloBoards();
        //THen
        assertEquals(1,newList.size());

    }
}