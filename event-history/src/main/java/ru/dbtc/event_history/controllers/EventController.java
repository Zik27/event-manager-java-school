package ru.dbtc.event_history.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dbtc.event_history.dto.UserEventHistoryDto;
import ru.dbtc.event_history.service.EventService;
@RestController
@RequestMapping("/history")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping
    public UserEventHistoryDto createEvent(@RequestParam String userId, @RequestParam int eventId){return eventService.createEvent(userId, eventId);}

    @DeleteMapping
    public UserEventHistoryDto deleteEvent(@RequestParam String userId, @RequestParam int eventId){
        return eventService.deleteEvent(userId, eventId);
    }

    @PatchMapping(value = "/{score}")
    public UserEventHistoryDto updateScore(@RequestBody UserEventHistoryDto userEventHistoryDto){
        return eventService.updateScore(userEventHistoryDto);
    }

}
