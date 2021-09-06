package ru.dbtc.event_history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dbtc.event_history.model.UserEventHistory;

import java.util.Optional;

@Repository
public interface UserEventHistoryRepo extends JpaRepository<UserEventHistory, Integer> {
    Optional<UserEventHistory> findByUserIdAndEventId(String userId, int eventId);
    void deleteByUserIdAndEventId(String userId, int eventId);
}
