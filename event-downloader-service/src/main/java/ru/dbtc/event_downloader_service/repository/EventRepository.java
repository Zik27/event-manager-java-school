package ru.dbtc.event_downloader_service.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.dbtc.event_downloader_service.models.Event;

public interface EventRepository extends ElasticsearchRepository<Event, Integer> {
}
