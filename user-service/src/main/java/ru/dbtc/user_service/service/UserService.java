package ru.dbtc.user_service.service;

import ru.dbtc.user_service.dto.UserDto;
import ru.dbtc.user_service.model.UserEntity;

public interface UserService {
    UserDto getUser(int id);

    UserDto addUser(UserDto userDto);

    UserDto deleteUser(int id);

    UserDto updateUser(UserDto userDto);
}
