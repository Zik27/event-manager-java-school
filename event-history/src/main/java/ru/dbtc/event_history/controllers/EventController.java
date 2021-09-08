package ru.dbtc.event_history.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dbtc.event_history.dto.UserEventHistoryDto;
import ru.dbtc.event_history.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/history")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping
    public UserEventHistoryDto createEvent(@RequestParam int userId, @RequestParam int eventId){
        return eventService.createEvent(userId, eventId);
    }

    @DeleteMapping
    public UserEventHistoryDto deleteEvent(@RequestParam int userId, @RequestParam int eventId){
        return eventService.deleteEvent(userId, eventId);
    }

    @PutMapping
    public UserEventHistoryDto updateScore(@RequestParam int score, @RequestParam int userId, @RequestParam int eventId){
        return eventService.updateScore(score, userId, eventId);
    }

    @GetMapping(value = "/{userId}")
    public List<UserEventHistoryDto> getAllEvents(@PathVariable int userId){
        return eventService.getAllEvents(userId);
    }


}
