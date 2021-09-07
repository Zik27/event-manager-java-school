package ru.dbtc.event_downloader_service.dto;


import lombok.Data;

import java.util.List;

@Data
public class EventResponseDto {
    private long total;

    private List<EventDto> values;
}
