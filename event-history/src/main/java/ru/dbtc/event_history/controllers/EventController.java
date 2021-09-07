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
    public UserEventHistoryDto createEvent(@RequestBody UserEventHistoryDto userEventHistoryDto){
        return eventService.createEvent(userEventHistoryDto);
    }

    @DeleteMapping
    public UserEventHistoryDto deleteEvent(@RequestParam int userId, @RequestParam int eventId){
        return eventService.deleteEvent(userId, eventId);
    }

    @PatchMapping(value = "/{score}")
    public UserEventHistoryDto updateScore(@RequestBody UserEventHistoryDto userEventHistoryDto){
        return eventService.updateScore(userEventHistoryDto);
    }

    @GetMapping(value = "/{userId}")
    public List<UserEventHistoryDto> getAllEvents(@PathVariable int userId){
        return eventService.getAllEvents(userId);
    }


}
