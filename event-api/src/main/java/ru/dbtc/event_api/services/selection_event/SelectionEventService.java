package ru.dbtc.event_api.services.selection_event;


public interface SelectionEventService {
    <T, S> T select(S user);
}
