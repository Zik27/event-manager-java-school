package ru.dbtc.event_downloader_service.services.downloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.dbtc.event_downloader_service.dto.EventResponseDto;

import java.util.List;

@Component
@PropertySource("classpath:timepad.properties")
public class EventDownloaderTimePad implements EventDownloader {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${timepad.events.url}")
    private String url;

    @Override
    @SneakyThrows
    public <T> List<T> getEvents(long skipEvents, int limitInRequest) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("limit", limitInRequest)
                .queryParam("skip", skipEvents)
                .queryParam("fields", "location,description_short,age_limit,ends_at")
                .queryParam("sort", "+starts_at")
                .queryParam("moderation_statuses", "featured,shown");

        String json = restTemplate.getForObject(builder.toUriString(), String.class);
        EventResponseDto eventResponseDto = objectMapper.readValue(json, EventResponseDto.class);
        return (List<T>) eventResponseDto.getValues();
    }

    @SneakyThrows
    public long getCountEvents() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("limit", 1)
                .queryParam("moderation_statuses", "featured,shown");

        return objectMapper.readValue(restTemplate.getForObject(builder.toUriString(), String.class), EventResponseDto.class)
                .getTotal();
    }
}
