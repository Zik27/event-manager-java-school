package ru.dbtc.event_history.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dbtc.event_history.dto.UserEventHistoryDto;
import ru.dbtc.event_history.model.UserEventHistory;
import ru.dbtc.event_history.repository.UserEventHistoryRepo;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    private UserEventHistoryRepo repo;

    private UserEventHistoryDto toUserEventHistoryDto(UserEventHistory user){return new UserEventHistoryDto(user.getId(), user.getUserId(), user.getEventId());}

    @Override
    public UserEventHistoryDto createEvent(String userId, int eventId) {
        UserEventHistory user = new UserEventHistory(userId, eventId);
        repo.save(user);
        return toUserEventHistoryDto(user);
    }
}
