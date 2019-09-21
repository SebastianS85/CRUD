package com.crud.tasks.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class CreatedTrelloCard {

    @JsonProperty("id")
    private String id;
    @JsonProperty("badges")
    private Badges badges;
    @JsonProperty("name")
    private String name;
    @JsonProperty("shortUrl")
    private String shortUrl;


}
