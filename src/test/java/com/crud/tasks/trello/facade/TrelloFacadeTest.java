package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {
    @InjectMocks
    private TrelloFacade trelloFacade;
    @Mock
    private TrelloService trelloService;
    @Mock
    private TrelloValidator trelloValidator;
    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchEmptyList() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "the list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "the list", trelloLists));

        List<TrelloList> mappedTrelloList = new ArrayList<>();
        mappedTrelloList.add(new TrelloList("1", "the list", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "the list", mappedTrelloList));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToTrelloBoardsDto(anyList())).thenReturn(new ArrayList<>());
        when(trelloValidator.valdiateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "the list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "the list", trelloLists));

        List<TrelloList> mappedTrelloList = new ArrayList<>();
        mappedTrelloList.add(new TrelloList("1", "the list", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "the list", mappedTrelloList));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToTrelloBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.valdiateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(1, trelloBoardDtos.size());
        trelloBoardDtos.forEach(trelloBoardDto -> {
            assertEquals("1", trelloBoardDto.getId());
            assertEquals("the list", trelloBoardDto.getName());

         trelloBoardDto.getLists().forEach(trelloListDto -> {
             assertEquals("1",trelloListDto.getId());
             assertEquals("the list",trelloListDto.getName());
             assertEquals(false,trelloListDto.isClosed());
         });
        });

    }
    @Test
    public void shouldCreateTrelloCardDto(){
        //Given
       TrelloCardDto trelloCardDto=new TrelloCardDto("test","test desc","top","testlist");
       TrelloCard trelloCard=new TrelloCard("test","test desc","top","testlist");
       CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1",new Badges(),"test","www.test.com");

       when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
       when(trelloMapper.mapToCardDto(trelloCard)).thenReturn(trelloCardDto);
       when(trelloService.createdTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);


        //When
       CreatedTrelloCardDto createdTrelloCardDto1=trelloFacade.createdCard(trelloCardDto);

       assertEquals("1",createdTrelloCardDto1.getId());
       assertEquals("test",createdTrelloCardDto1.getName());
       assertEquals("www.test.com",createdTrelloCardDto1.getShortUrl());

    }

}
