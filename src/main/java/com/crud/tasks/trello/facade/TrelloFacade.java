package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class TrelloFacade {

    private final static Logger LOGGER = LoggerFactory.getLogger(TrelloFacade.class);

    @Autowired
    TrelloMapper trelloMapper;
    @Autowired
    private TrelloService trelloService;
    @Autowired
    private TrelloValidator trelloValidator;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloService.fetchTrelloBoards());
        List<TrelloBoard> filteredBoards = trelloValidator.valdiateTrelloBoards(trelloBoards);
        return trelloMapper.mapToTrelloBoardsDto(filteredBoards);
    }

    public CreatedTrelloCardDto createdCard(final TrelloCardDto trelloCardDto) {
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        trelloValidator.vaildateCard(trelloCard);
        return trelloService.createdTrelloCard(trelloMapper.mapToCardDto(trelloCard));
    }
}
