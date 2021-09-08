package ru.dbtc.event_history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dbtc.event_history.model.UserEventHistory;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEventHistoryRepo extends JpaRepository<UserEventHistory, Integer> {
    Optional<UserEventHistory> findByUserIdAndEventId(int userId, int eventId);
    void deleteByUserIdAndEventId(int userId, int eventId);
    Optional<List<UserEventHistory>> findByUserId(int userId);
}
