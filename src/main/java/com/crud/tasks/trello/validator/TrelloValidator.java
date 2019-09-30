package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloValidator {
    private final static Logger LOGGER = LoggerFactory.getLogger(TrelloValidator.class);

    public void vaildateCard(final TrelloCard trelloCard) {
        if (trelloCard.getName().contains("test")) {
            LOGGER.info("somebody is testing my app");
        } else {
            LOGGER.info("seems ok");
        }
    }

    public List<TrelloBoard> valdiateTrelloBoards(final List<TrelloBoard> trelloBoards) {
        LOGGER.info("Starting filtering boards...");
        List<TrelloBoard> filteredBoards = trelloBoards.stream()
                .filter(trelloBoard -> !trelloBoard.getName().equalsIgnoreCase("test"))
                .collect(Collectors.toList());
        LOGGER.info("Boards has been filtered current List size is: " + filteredBoards.size());
        return filteredBoards;
    }
}