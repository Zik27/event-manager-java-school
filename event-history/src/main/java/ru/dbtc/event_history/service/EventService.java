package ru.dbtc.event_history.service;


import ru.dbtc.event_history.dto.UserEventHistoryDto;

import java.util.List;

public interface EventService {
    UserEventHistoryDto createEvent(UserEventHistoryDto userEventHistoryDto);

    UserEventHistoryDto deleteEvent(int userId, int eventId);

    UserEventHistoryDto updateScore(UserEventHistoryDto userEventHistoryDto);

    List<UserEventHistoryDto> getAllEvents(int userId);
}