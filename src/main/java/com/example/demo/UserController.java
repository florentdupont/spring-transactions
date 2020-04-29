package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class UserController {

    @Autowired
    UserFacade userFacade;

//    @Autowired
//    TransactionalUserService tUserService;

    @GetMapping("/users")
    @ResponseStatus(OK)
    public List<UserDto> users() {
        return userFacade.findUsers();
    }

    @PostMapping("/users")
    @ResponseStatus(CREATED)
    public void create(@RequestBody UserDto userDto) {
        userFacade.create(userDto);
    }
}
