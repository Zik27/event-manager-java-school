package ru.dbtc.event_history.service;


import ru.dbtc.event_history.dto.UserEventHistoryDto;

public interface EventService {
    UserEventHistoryDto createEvent(String userId, int eventId);
}