package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mapToTrelloBoard() {
        //Given
        TrelloListDto listDto = new TrelloListDto("1", "list", false);
        List<TrelloListDto> theList = new ArrayList<>();
        theList.add(listDto);
        TrelloBoardDto boardDto = new TrelloBoardDto("1", "first board", theList);
        //When
        TrelloBoard mappedBoard = trelloMapper.mapToBoard(boardDto);
        //Then
        assertEquals("1", mappedBoard.getId());
        assertEquals("first board", mappedBoard.getName());
        assertEquals(1, mappedBoard.getLists().size());

    }

    @Test
    public void mapToTrelloBoardsList() {
        //Given
        TrelloListDto trelloList = new TrelloListDto("1", "test list", true);
        List<TrelloListDto> list = new ArrayList<>();
        list.add(trelloList);
        TrelloBoardDto boardDto = new TrelloBoardDto("1", "first board", list);
        TrelloBoardDto boardDto1 = new TrelloBoardDto("1", "first board", list);
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(boardDto);
        trelloBoardDtoList.add(boardDto1);
        //When
        List<TrelloBoard> theList = trelloMapper.mapToBoards(trelloBoardDtoList);
        assertEquals(2, theList.size());

    }

    @Test
    public void mapToTrelloBoardsDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "test list", true);
        List<TrelloList> list = new ArrayList<>();
        list.add(trelloList);
        TrelloBoard trelloBoard = new TrelloBoard("1", "first", list);
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "first", list);
        List<TrelloBoard> theList = new ArrayList<>();
        theList.add(trelloBoard);
        theList.add(trelloBoard1);
        //When
        List<TrelloBoardDto> mappedList = trelloMapper.mapToTrelloBoardsDto(theList);
        //Then
        assertEquals(2, mappedList.size());
    }

    @Test
    public void mapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "test list", true);
        List<TrelloListDto> theList = new ArrayList<>();
        theList.add(trelloListDto);
        //When
        List<TrelloList> mappedList = trelloMapper.mapToList(theList);
        //Then
        assertEquals(1, mappedList.size());
    }

    @Test
    public void mapToListDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "test list", true);
        List<TrelloList> theList = new ArrayList<>();
        theList.add(trelloList);
        //When
        List<TrelloListDto> mappedList = trelloMapper.mapToListDto(theList);
        //Then
        assertEquals(1, mappedList.size());
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test card", "test desc", "top", "1");
        //When
        TrelloCardDto mappedCard = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("test card", mappedCard.getName());
        assertEquals("test desc", mappedCard.getDescription());
        assertEquals("top", mappedCard.getPos());
        assertEquals("1", mappedCard.getIdList());
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test card", "test desc", "top", "1");
        //When
        TrelloCard mappedCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("test card", mappedCard.getName());
        assertEquals("test desc", mappedCard.getDescription());
        assertEquals("top", mappedCard.getPos());
        assertEquals("1", mappedCard.getListId());
    }

}
