package ru.dbtc.event_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.dbtc.event_api.models.Event;
import ru.dbtc.event_api.repository.EventRepository;
import ru.dbtc.event_api.utils.RandomGenerator;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RandomGenerator randomGenerator;

    public Event getEventById(int id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event with id [" + id + "] was not found"));
    }

    public List<Event> getTop10EventsByCity(String city) {
        return eventRepository.findTop10EventsByCity(city)
                .filter(e -> !e.isEmpty())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Events were not found by city [" + city + "]"));
    }

    public List<Event> getTop10EventsByCityAndAgeLimitLessThan(String city, int ageLimit) {
        return eventRepository.findTop10EventsByCityAndAgeLimitLessThan(city, ageLimit)
                .filter(e -> !e.isEmpty())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Events were not found by city [" + city + "] and age limit [" + ageLimit + "]"));
    }

    public Event getRandEventByCity(String city) {
        int seed = randomGenerator.generateRndNumberInRange(1, Integer.MAX_VALUE);
        return eventRepository.findRandEventByCity(city, seed)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Random events in city [" + city + "] were not found"));
    }

    public List<Event> getTop10EventsByDescription(String description) {
        return eventRepository.findTop10EventsByDescription(description)
                .filter(e -> !e.isEmpty())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Events were not found by description [" + description + "]"));
    }

    public List<Event> getTop10EventsByCategory(String category) {
        return eventRepository.findTop10EventsByCategory(category)
                .filter(e -> !e.isEmpty())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Events were not found by category [" + category + "]"));
    }

    public List<Event> getTop10EventsByTitle(String title) {
        return eventRepository.findTop10EventsByTitle(title)
                .filter(e -> !e.isEmpty())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Events were not found by title [" + title + "]"));
    }

    public List<Event> getTop10EventsByCityAndAddress(String city, String address) {
        return eventRepository.findTop10EventsByCityAndAddress(city, address)
                .filter(e -> !e.isEmpty())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Events were not found by city [" + city + "] and address [" + address + "]"));
    }

    public void deleteEventById(int id) {
        eventRepository.deleteById(id);
    }

    public void createEventById(Event event) {
        eventRepository.save(event);
    }

    public void updateAddressOfEvent(Integer id, String newAddress) {
        Event eventById = getEventById(id);
    }

//    public String deleteEventsBeforeDate(Date date) {
//        eventRepository.find
//    }
}
