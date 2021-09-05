package ru.dbtc.user_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.dbtc.user_service.repositories.UserRepo;
import ru.dbtc.user_service.model.UserEntity;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserEntity getUser(int id) {
        return repo.findById(id).get();
    }

    @Override
    public String addUser(int telegramId, String name) {
        UserEntity user = repo.findById(telegramId).orElse(null);
        if (user != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User id: " + telegramId + " already exists");
        }
        user = new UserEntity(telegramId, name);
        repo.save(user);
        return "User added";
    }

//    @Override
//    public String updateUser(UserRepo userRepo) {
//        UserEntity userEntity = repo.findById(userRepo.get)
//    }
}
