package ru.dbtc.event_downloader_service.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dbtc.event_downloader_service.EventTimePadDownloadScheduler;

@RestController
@RequestMapping("/api")
public class EventController {
    @Autowired
    private EventTimePadDownloadScheduler timepadService;

    @GetMapping("/events")
    public String getEvents() {
        return timepadService.getTimepadEvents();
    }
}
