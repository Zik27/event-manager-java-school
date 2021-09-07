package ru.dbtc.event_history.service;


import ru.dbtc.event_history.dto.UserEventHistoryDto;

public interface EventService {
    UserEventHistoryDto createEvent(String userId, int eventId);

    UserEventHistoryDto deleteEvent(String userId, int eventId);

    UserEventHistoryDto updateScore(UserEventHistoryDto userEventHistoryDto);
}