package ru.dbtc.event_api.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;
import ru.dbtc.event_api.models.Event;
import ru.dbtc.event_api.services.EventService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Api(tags="Event-API")
@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable int id) {
        return eventService.getEventById(id);
    }

    @GetMapping("/location")
    public List<Event> getTop10EventsByCity(@RequestParam String city) {
        return eventService.getTop10EventsByCity(city);
    }

    @GetMapping("/location/address")
    public List<Event> getTop10EventsByCityAndAddress(@RequestParam String city, @RequestParam String address) {
        return eventService.getTop10EventsByCityAndAddress(city, address);
    }

    @GetMapping
    public List<Event> getTop10EventsByCityAndLessThanAgeLimit(@RequestParam String city, @RequestParam int ageLimit) {
        return eventService.getTop10EventsByCityAndAgeLimitLessThan(city, ageLimit);
    }

    @GetMapping("/random")
    public Event getRandEventByCity(@RequestParam String city) {
        return eventService.getRandEventByCity(city);
    }

    @GetMapping("/keywords/description")
    public List<Event> getTop10EventsByDescription(@RequestParam String description) {
        return eventService.getTop10EventsByDescription(description);
    }

    @GetMapping("/keywords")
    public Event getEventByDescriptionAndCityAndStartEventAfter(@RequestParam String description, @RequestParam String city, @RequestParam String startEvent) {
        return eventService.getEventByDescriptionAndCityAndStartEvent(description, city, startEvent);
    }

    @GetMapping("/categories/{category}")
    public List<Event> getTop10EventsByCategory(@PathVariable String category) {
        return eventService.getTop10EventsByCategory(category);
    }

    @GetMapping("/title")
    public List<Event> getTop10EventsByTitle(@RequestParam String title) {
        return eventService.getTop10EventsByTitle(title);
    }

    @DeleteMapping("/{id}")
    public void deleteEventById(@PathVariable int id) {
        eventService.deleteEventById(id);
    }

    @PostMapping("/create")
    public void createEventById(@RequestBody Event event) {
        eventService.createEventById(event);
    }

    @PutMapping("/location/address")
    public void updateAddressOfEvent(@RequestParam int eventId, @RequestParam String newAddress) {
        eventService.updateAddressOfEvent(eventId, newAddress);
    }

//    @GetMapping("/selection")
//    public Event selectEventByFilters(@RequestParam String userId, @RequestParam Date date, @RequestParam String[] filters) {
//        System.out.println("userId = " + userId + ", filters = " + Arrays.deepToString(filters));
//        return eventService.selectEventByFilters(userId, date, filters);
////        return null;
//    }

//    @DeleteMapping("/deleteByDate")
//    public String deleteEventsBeforeDate(@RequestParam Date date) {
//        return eventService.deleteEventsBeforeDate(date);
//    }
}
