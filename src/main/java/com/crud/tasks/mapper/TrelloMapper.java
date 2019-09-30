package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloMapper {

    public TrelloBoard mapToBoard(TrelloBoardDto boardDto) {
        return new TrelloBoard(boardDto.getId(), boardDto.getName(), mapToList(boardDto.getLists()));
    }

    public List<TrelloBoard> mapToBoards(final List<TrelloBoardDto> trelloBoardDtoList) {
        return trelloBoardDtoList.stream()
                .map(trelloBoard ->
                        new TrelloBoard(trelloBoard.getId(), trelloBoard.getName(), mapToList(trelloBoard.getLists())))
                .collect(Collectors.toList());
    }

    public List<TrelloBoardDto> mapToTrelloBoardsDto(final List<TrelloBoard> trelloBoards) {
        return trelloBoards.stream()
                .map(trelloBoard ->
                        new TrelloBoardDto(trelloBoard.getId(), trelloBoard.getName(), mapToListDto(trelloBoard.getLists())))
                .collect(Collectors.toList());
    }


    public List<TrelloList> mapToList(final List<TrelloListDto> trelloListDto) {
        return trelloListDto.stream()
                .map(trelloList ->
                        new TrelloList(trelloList.getId(), trelloList.getName(), trelloList.isClosed()))
                .collect(Collectors.toList());

    }

    public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloLists) {
        return trelloLists.stream()
                .map(trelloList ->
                        new TrelloListDto(trelloList.getId(), trelloList.getName(), trelloList.isClosed()))
                .collect(Collectors.toList());
    }

    public TrelloCardDto mapToCardDto(final TrelloCard trelloCard) {
        return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(), trelloCard.getPos(), trelloCard.getListId());
    }

    public TrelloCard mapToCard(final TrelloCardDto trelloCard) {
        return new TrelloCard(trelloCard.getName(), trelloCard.getDescription(), trelloCard.getPos(), trelloCard.getIdList());
    }


}
