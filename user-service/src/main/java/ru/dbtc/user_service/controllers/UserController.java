package ru.dbtc.user_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dbtc.user_service.dto.UserDto;
import ru.dbtc.user_service.model.UserEntity;
import ru.dbtc.user_service.service.UserService;

import static ru.dbtc.user_service.api.ApiConstants.USERS;

@RestController
@RequestMapping(USERS)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public UserDto getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @DeleteMapping(value = "{id}")
    public UserDto deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }

    @PatchMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

}
