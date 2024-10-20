package me.dio.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserListDTO {

    @JsonProperty("pageable")
    private SimplePageable pageable;

    @JsonProperty("content")
    private List<ListUserDto> users;
}
