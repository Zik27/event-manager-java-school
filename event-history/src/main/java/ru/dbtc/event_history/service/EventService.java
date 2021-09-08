package ru.dbtc.event_history.service;


import ru.dbtc.event_history.dto.UserEventHistoryDto;

import java.util.List;

public interface EventService {
    UserEventHistoryDto createEvent(int userId, int eventId);

    UserEventHistoryDto deleteEvent(int userId, int eventId);

    UserEventHistoryDto updateScore(int score, int userId, int eventId);

    List<UserEventHistoryDto> getAllEvents(int userId);
}