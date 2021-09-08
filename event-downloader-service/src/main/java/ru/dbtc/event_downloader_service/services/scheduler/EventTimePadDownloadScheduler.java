package ru.dbtc.event_downloader_service.services.scheduler;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.dbtc.event_downloader_service.dto.EventDto;
import ru.dbtc.event_downloader_service.models.Event;
import ru.dbtc.event_downloader_service.repository.EventRepository;
import ru.dbtc.event_downloader_service.services.downloader.EventDownloader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EventTimePadDownloadScheduler implements EventDownloadScheduler {
    private static final int MAX_COUNT_EVENTS_IN_REQUEST = 100;

    @Autowired
    private EventDownloader eventDownloader;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @SneakyThrows
    @Scheduled(fixedDelay = 30 * 60 * 1000)
    public void execute() {
        List<EventDto> allEventsDto = new ArrayList<>();
        long countEvents = eventDownloader.getCountEvents();

        for (long processedEvents = 0; processedEvents < countEvents; processedEvents += MAX_COUNT_EVENTS_IN_REQUEST) {
            Thread.sleep(1000);
            List<EventDto> events = eventDownloader.getEvents(processedEvents, MAX_COUNT_EVENTS_IN_REQUEST);
            allEventsDto.addAll(events);
        }

        List<Event> allEvents = allEventsDto.stream()
                .map(eventDto -> modelMapper.map(eventDto, Event.class))
                .collect(Collectors.toList());

        eventDownloader.saveEvent(allEvents);
    }
}
