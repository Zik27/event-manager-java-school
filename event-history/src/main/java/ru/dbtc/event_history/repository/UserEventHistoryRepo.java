package ru.dbtc.event_history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dbtc.event_history.model.UserEventHistory;

@Repository
public interface UserEventHistoryRepo extends JpaRepository<UserEventHistory, Integer> {
}
