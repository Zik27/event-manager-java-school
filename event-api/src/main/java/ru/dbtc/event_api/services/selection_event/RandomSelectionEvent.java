package ru.dbtc.event_api.services.selection_event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dbtc.event_api.services.EventService;


@Component
public class RandomSelectionEvent implements SelectionEventService {

    @Autowired
    private EventService eventService;

    @Override
    public <T, S> T select(S user) {
//        String city = "HA";
//        return eventService.getRandEventByCity(city);
        return null;
    }
}
