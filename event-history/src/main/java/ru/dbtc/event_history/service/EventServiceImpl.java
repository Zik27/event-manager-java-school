package ru.dbtc.event_history.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.dbtc.event_history.dto.UserEventHistoryDto;
import ru.dbtc.event_history.model.UserEventHistory;
import ru.dbtc.event_history.repository.UserEventHistoryRepo;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    private UserEventHistoryRepo repo;

    private UserEventHistoryDto toUserEventHistoryDto(UserEventHistory user){
        return new UserEventHistoryDto(user.getId(), user.getUserId(), user.getEventId());
    }

    @Override
    public UserEventHistoryDto createEvent(UserEventHistoryDto userEventHistoryDto) {
        UserEventHistory user = UserEventHistory.builder()
                        .userId(userEventHistoryDto.getUserId())
                        .eventId(userEventHistoryDto.getEventId())
                        .build();
        repo.save(user);
        return toUserEventHistoryDto(user);
    }

    @Override
    public UserEventHistoryDto deleteEvent(int userId, int eventId) {
        UserEventHistory userEventHistory = repo.findByUserIdAndEventId(userId, eventId)
                .orElseThrow(()->{throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                });
        repo.deleteByUserIdAndEventId(userId, eventId);
        return toUserEventHistoryDto(userEventHistory);
    }

    @Override
    public UserEventHistoryDto updateScore(UserEventHistoryDto userEventHistoryDto) {
        UserEventHistory userEventHistory = repo.findByUserIdAndEventId(userEventHistoryDto.getUserId(),userEventHistoryDto.getEventId())
                .orElseThrow(()->{throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                });
        userEventHistory.setScore(userEventHistory.getScore());
        return toUserEventHistoryDto(userEventHistory);
    }

    @Override
    public List<UserEventHistoryDto> getAllEvents(int userId) {
        List<UserEventHistory> allEvents = repo
                .findByUserId(userId)
                .orElseThrow(()->{throw new ResponseStatusException(HttpStatus.NOT_FOUND);});
        return allEvents.stream().map(this::toUserEventHistoryDto).collect(toList());
    }


}
