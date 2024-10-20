package me.dio.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
public class SimplePageable implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("_moreElements")
    private Boolean moreElements;

    public SimplePageable() {
        this.moreElements = Boolean.FALSE;
    }

    public Boolean isMoreElements() {
        return this.moreElements;
    }
}
