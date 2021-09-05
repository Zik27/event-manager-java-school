package ru.dbtc.user_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dbtc.user_service.model.UserEntity;
import ru.dbtc.user_service.services.UserService;

import static ru.dbtc.user_service.api.ApiConstants.USERS;

@RestController
@RequestMapping(USERS)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public UserEntity getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @PostMapping
    public String addUser(@PathVariable int telegramId, @PathVariable String name) {
        return userService.addUser(telegramId,name);
    }

//    @PatchMapping
//    public String updateUser(@RequestBody UserRepo userRepo) {
//        return userService.updateUser(userRepo);
//    }
}
