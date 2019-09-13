package com.crud.tasks.trello.client;


import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
public class TrelloClient {
    @Autowired
    RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint ;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.username}")
    private String username;

    private URI url;

    public List<TrelloBoardDto>getTrelloBoards( ) {
        this.url=buildURL();


        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

        return Arrays.asList(Optional.ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));


    }
    private URI buildURL(){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/"+username+"/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all").build().encode().toUri();
       return url;
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){
        URI url= UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint+"/cards")
                .queryParam("name",trelloCardDto.getName())
                .queryParam("desc",trelloCardDto.getDescription())
                .queryParam("pos",trelloCardDto.getPos())
                .queryParam("idList",trelloCardDto.getIdList())
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .build().encode().toUri();

        System.out.println(trelloCardDto.getIdList());
        System.out.println(trelloCardDto.getPos());
        System.out.println(trelloCardDto.getName());
        System.out.println(url);
        return restTemplate.postForObject(url,null,CreatedTrelloCard.class);
    }
}
