package com.crud.tasks.trello.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.Badges;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private TrelloConfig trelloConfig;


    @Before
    public void init(){
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getUsername()).thenReturn("sebas463");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
    }
    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {




        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];

        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

        URI uri = new URI("http://test.com/members/sebas463/boards" +
                "?key=test&token=test&fields=name,id&lists=all");
        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);
        //When

        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();


        //Then
        Assert.assertEquals(1, fetchedTrelloBoards.size());
        Assert.assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        Assert.assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
        Assert.assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());

    }

    @Test
    public void shouldCreateCard() throws URISyntaxException {
        //Given
        Badges badges=new Badges();
        TrelloCardDto trelloCardDto= new TrelloCardDto(
                "Test Task",
                "Test Description",
                "top",
                "test_id"
        );
        URI url = new URI("http://test.com/cards?name=Test%20Task&desc=Test%20Description&pos=top&idList=test_id&key=test&token=test");

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "1",
                badges,
                "Test Task",
                "http://test.com"
        );

        when( restTemplate.postForObject(url, null, CreatedTrelloCardDto.class)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto newCard= trelloClient.createNewCard(trelloCardDto);


        System.out.println(url);
        //Then
        Assert.assertEquals("1",newCard.getId());
        Assert.assertEquals("Test Task",newCard.getName());
        Assert.assertEquals("http://test.com",newCard.getShortUrl());
    }
    @Test
    public void shouldReturnEmptyList()throws URISyntaxException {
        //Given
        URI uri = new URI("http://test.com/members/sebas463/boards" +
                "?key=test&token=test&fields=name,id&lists=all");

        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(null);
        //When

        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();
        //Then
        Assert.assertEquals(0,fetchedTrelloBoards.size());

    }
}