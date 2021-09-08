package ru.dbtc.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.dbtc.user_service.dto.UserDto;
import ru.dbtc.user_service.exceptions.NotFoundException;
import ru.dbtc.user_service.repository.UserRepo;
import ru.dbtc.user_service.model.UserEntity;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo repo;

    private UserDto toUserDto(UserEntity user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getAge());
    }


    @Override
    public UserDto getUser(int id) {
        return toUserDto(repo
                .findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id:" + id + " not found");
                }));
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        UserEntity user = repo.findById(userDto.getId()).orElse(null);
        if (user != null) {
            throw new NotFoundException();
        }

        user = UserEntity.builder()
                .userName(user.getUserName())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .age(user.getAge())
                .id(userDto.getId())
                .build();
        repo.save(user);
        return toUserDto(user);
    }


    @Override
    public UserDto deleteUser(int id) {
        UserEntity userEntity = repo.findById(id)
                .orElseThrow(NotFoundException::new);
        repo.deleteById(id);
        return toUserDto(userEntity);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserEntity userEntity = repo.findById(userDto.getId())
                .orElseThrow(NotFoundException::new);
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setUserName(userDto.getUserName());
        userEntity.setAge(userDto.getAge());
        repo.save(userEntity);
        return toUserDto(userEntity);
    }

}
