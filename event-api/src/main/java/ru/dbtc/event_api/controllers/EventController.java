package ru.dbtc.event_api.controllers;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dbtc.event_api.models.Event;
import ru.dbtc.event_api.services.EventService;

import java.util.List;

@Api(tags="Event-API")
@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    @Operation(summary = "Get event by ID")
    public Event getEventById(@PathVariable int id) {
        return eventService.getEventById(id);
    }

    @GetMapping("/location")
    @Operation(summary = "Get 10 events by city")
    public List<Event> getTop10EventsByCity(@RequestParam String city) {
        return eventService.getTop10EventsByCity(city);
    }

    @GetMapping("/location/address")
    @Operation(summary = "Get 10 events by city and address")
    public List<Event> getTop10EventsByCityAndAddress(@RequestParam String city, @RequestParam String address) {
        return eventService.getTop10EventsByCityAndAddress(city, address);
    }

    @GetMapping
    @Operation(summary = "Get 10 events by city and age and values less than the age limit")
    public List<Event> getTop10EventsByCityAndLessThanAgeLimit(@RequestParam String city, @RequestParam int ageLimit) {
        return eventService.getTop10EventsByCityAndAgeLimitLessThan(city, ageLimit);
    }

    @GetMapping("/random")
    @Operation(summary = "Get random event by city")
    public Event getRandEventByCity(@RequestParam String city) {
        return eventService.getRandEventByCity(city);
    }

    @GetMapping("/keywords/description")
    @Operation(summary = "Get 10 events by the event description")
    public List<Event> getTop10EventsByDescription(@RequestParam String description) {
        return eventService.getTop10EventsByDescription(description);
    }

    @GetMapping("/keywords")
    @Operation(summary = "Get event by the event description, city and the start date of the event")
    public Event getEventByDescriptionAndCityAndStartEvent(@RequestParam String description, @RequestParam String city, @RequestParam String startEvent) {
        return eventService.getEventByDescriptionAndCityAndStartEvent(description, city, startEvent);
    }

    @GetMapping("/categories/{category}")
    @Operation(summary = "Get 10 events by category")
    public List<Event> getTop10EventsByCategory(@PathVariable String category) {
        return eventService.getTop10EventsByCategory(category);
    }

    @GetMapping("/title")
    @Operation(summary = "Get 10 events by title")
    public List<Event> getTop10EventsByTitle(@RequestParam String title) {
        return eventService.getTop10EventsByTitle(title);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete event by ID")
    public void deleteEventById(@PathVariable int id) {
        eventService.deleteEventById(id);
    }

    @PostMapping("/create")
    @Operation(summary = "Create event")
    public void createEventById(@RequestBody Event event) {
        eventService.createEventById(event);
    }

    @PutMapping("/location/address")
    @Operation(summary = "Update address of event by event ID and new address")
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
