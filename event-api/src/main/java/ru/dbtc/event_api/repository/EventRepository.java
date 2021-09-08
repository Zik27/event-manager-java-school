package ru.dbtc.event_api.repository;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.dbtc.event_api.models.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends ElasticsearchRepository<Event, Integer> {
    Optional<List<Event>> findTop10EventsByCity(String city);

    Optional<List<Event>> findTop10EventsByCityAndAddress(String city, String address);

    Optional<List<Event>> findTop10EventsByCityAndAgeLimitLessThan(String city, int ageLimit);

    Optional<List<Event>> findTop10EventsByDescription(String description);

    @Query("{\"bool\": {\"must\": [ {\"match\": {\"description\": \"?0\"} }, {\"match\": {\"city\": \"?1\"} }, {\"match\": {\"startEvent\": \"?2\"} } ] } }")
    Event findTop1ByDescriptionAndCityAndStartEvent(String description, String city, String startEvent);

    Optional<List<Event>> findTop10EventsByTitle(String title);

    @Query("{\"match\": {\"categories\" : \"?0\"} } }")
    Optional<List<Event>> findTop10EventsByCategory(String category);

    @Query("{\"function_score\": {\"query\": {\"match\": {\"city\": \"?0\"} }, \"random_score\": {\"seed\": ?1, \"field\": \"id\"} } }")
    Optional<Event> findRandEventByCity(String city, int seed);
}
