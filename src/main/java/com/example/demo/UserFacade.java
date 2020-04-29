package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserFacade {

    @Autowired
    UserService2 userService;

    @Autowired
    UserMapper userMapper;

    public List<UserDto> findUsers() {
        return userMapper.toDtos(userService.findUsers());
    }


    public void create(UserDto user) {
        userService.create(userMapper.toEntity(user));
    }
}
