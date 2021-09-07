package ru.dbtc.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dbtc.user_service.model.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
}
