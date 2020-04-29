package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.NEVER;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;


/*
  Là, on fait l'inverse de UserService2.
  La classe est marquée comme transactionnelle, mais les méthodes qui ne doivent pas l'etre sont exclues

  Ca fonctionne aussi !


 */
@Service
@Transactional
public class UserService3 {

    @Autowired
    UserRepository userRepository;

    @Transactional(propagation = NEVER)
    public List<User> findUsers() {

        tooLongOperation();

        List<User> users = getUsers();

        tooLongOperation();

        return users;
    }

    @Transactional(propagation = NEVER)
    public void tooLongOperation() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional(propagation = REQUIRED)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional(propagation = REQUIRED)
    public void create(User user) {
        userRepository.save(user);
    }


}
