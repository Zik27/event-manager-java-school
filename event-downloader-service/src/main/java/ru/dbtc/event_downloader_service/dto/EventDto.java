package ru.dbtc.event_downloader_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EventDto {

    private Integer id;

    @JsonProperty("name")
    private String title;

    private List<CategoryDto> categories;

    @JsonProperty("age_limit")
    private int ageLimit;

    //private boolean isFree;

    @JsonProperty("description_short")
    private String description;

    private String url;

    @JsonProperty("starts_at")
    private Date startEvent;

    @JsonProperty("ends_at")
    private Date endEvent;

    private LocationDto location;
}
