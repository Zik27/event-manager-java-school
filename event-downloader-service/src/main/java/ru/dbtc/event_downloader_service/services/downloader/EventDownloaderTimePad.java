package ru.dbtc.event_downloader_service.services.downloader;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import ru.dbtc.event_downloader_service.dto.EventDto;
import ru.dbtc.event_downloader_service.dto.EventResponseDto;
import ru.dbtc.event_downloader_service.models.Event;
import ru.dbtc.event_downloader_service.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Component
@PropertySource("classpath:timepad.properties")
public class EventDownloaderTimePad implements EventDownloader {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EventRepository eventRepository;

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

        List<EventDto> eventResponseDto = Optional.ofNullable(restTemplate.getForObject(builder.toUriString(), EventResponseDto.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error when receiving events from url: " + builder.toUriString()))
                .getValues();
        return (List<T>) eventResponseDto;
    }

    @SneakyThrows
    public long getCountEvents() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("limit", 1)
                .queryParam("moderation_statuses", "featured,shown");

        return Optional.ofNullable(restTemplate.getForObject(builder.toUriString(), EventResponseDto.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error when receiving count of events from url: " + builder.toUriString()))
                .getTotal();
    }

    @Override
    public void saveEvent(List<Event> events) {
        eventRepository.saveAll(events);
    }
}
