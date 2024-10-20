package me.dio.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListUserDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;
}
