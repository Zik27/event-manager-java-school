package ru.dbtc.user_service.services;

import ru.dbtc.user_service.model.UserEntity;

public interface UserService {
    UserEntity getUser(int id);

    String addUser(int telegramId, String name);

//    String updateUser(UserRepo userRepo);
}
