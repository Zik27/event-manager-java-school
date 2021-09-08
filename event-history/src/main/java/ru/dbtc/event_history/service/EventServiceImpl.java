package ru.dbtc.event_history.service;

import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

//    private UserEventHistoryDto toUserEventHistoryDto(UserEventHistory user){
//        return new UserEventHistoryDto(user.getId(), user.getUserId(), user.getEventId());
//    }

    @Override
    public UserEventHistoryDto createEvent(int userId, int eventId) {
        UserEventHistory user = UserEventHistory.builder()
                        .userId(userId)
                        .eventId(eventId)
                        .build();
        repo.save(user);
        return modelMapper.map(user, UserEventHistoryDto.class);
    }

    @Override
    public UserEventHistoryDto deleteEvent(int userId, int eventId) {
        UserEventHistory userEventHistory = repo.findByUserIdAndEventId(userId, eventId)
                .orElseThrow(()->{throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                });
        repo.deleteByUserIdAndEventId(userId, eventId);
        return modelMapper.map(userEventHistory, UserEventHistoryDto.class);
    }

    @Override
    public UserEventHistoryDto updateScore(int score, int userId, int eventId) {
        UserEventHistory userEventHistory = repo.findByUserIdAndEventId(userId, eventId)
                .orElseThrow(()->{throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                });
        userEventHistory.setScore(score);
        repo.save(userEventHistory);
        return modelMapper.map(userEventHistory, UserEventHistoryDto.class);
    }

    @Override
    public List<UserEventHistoryDto> getAllEvents(int userId) {
        List<UserEventHistory> allEvents = repo
                .findByUserId(userId)
                .orElseThrow(()->{throw new ResponseStatusException(HttpStatus.NOT_FOUND);});
        return allEvents.stream()
                .map(userEventHistory-> modelMapper.map(userEventHistory, UserEventHistoryDto.class))
                .collect(toList());
    }


}
