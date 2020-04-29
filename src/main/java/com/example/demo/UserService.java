package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<User> findUsers() {

        tooLongOperation();

        List<User> users = userRepository.findAll();

        tooLongOperation();

        return users;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    private void tooLongOperation() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
