package com.crud.tasks.trello.client;


import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TrelloClient {
    @Autowired
    RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.username}")
    private String username;

    private URI url;

    public List<TrelloBoardDto> getTrelloBoards() {
        this.url = buildURL();


        List<Optional<TrelloBoardDto>> boardsList =
                Arrays.stream(restTemplate.getForObject(url, TrelloBoardDto[].class))
                        .map(Optional::ofNullable)
                        .collect(Collectors.toList());

        return boardsList.stream().map(s -> s.get()).collect(Collectors.toList());

    }

    private URI buildURL() {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + username + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id").build().encode().toUri();
        return url;
    }
}
