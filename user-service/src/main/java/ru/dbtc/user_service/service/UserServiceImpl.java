package ru.dbtc.user_service.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dbtc.user_service.dto.UserDto;
import ru.dbtc.user_service.exceptions.NotFoundException;
import ru.dbtc.user_service.repository.UserRepo;
import ru.dbtc.user_service.model.UserEntity;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto getUser(int id) {
        UserEntity userEntity = repo.findById(id)
                .orElseThrow(NotFoundException::new);
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        UserEntity user = repo.findById(userDto.getId()).orElse(null);

        if (user!=null){
            throw new NotFoundException();
        }
        user = UserEntity.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .userName(userDto.getUserName())
                .birthYear(userDto.getBirthYear())
                .city(userDto.getCity())
                .build();
        repo.save(user);
        return modelMapper.map(user, UserDto.class);
    }
  
    @Override
    public UserDto deleteUser(int id) {
        UserEntity userEntity = repo.findById(id)
                .orElseThrow(NotFoundException::new);
        repo.deleteById(id);
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserEntity userEntity = repo.findById(userDto.getId())
                .orElseThrow(NotFoundException::new);
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setUserName(userDto.getUserName());
        userEntity.setBirthYear(userDto.getBirthYear());
        userEntity.setCity(userDto.getCity());
        repo.save(userEntity);
        return modelMapper.map(userEntity, UserDto.class);
    }
}
