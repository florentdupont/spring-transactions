package com.example.demo;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.*;
import static org.springframework.transaction.support.TransactionSynchronizationManager.isActualTransactionActive;


/*
  FOnctionne !

  La classe globale est non transactionnelle en readonly.
  Par contre, seules les méthodes trnasactionnelles sont indiquées comme telles.

  Atention, avec SpringData JPA, par défaut les Repository sont trnasactionnel, ie. que s'il sont utilisés
  hors transaction, alors une nouvelle connexion sera ouverte!!

  le @Transactional différent doit être appelé depuis une autre classe.

 */
@Service
@Transactional(propagation = NEVER)
public class UserService2 {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionalUserService2 transactionalUserService2;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Timed
    public List<User> findUsers() {
        System.out.println("tooLongOperation - active ? " + isActualTransactionActive());

        tooLongOperation();

        List<User> result = transactionalUserService2.getUsers();

        tooLongOperation();

        return result;

    }

    public void tooLongOperation() {
        System.out.println("tooLongOperation - active ? " + isActualTransactionActive());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // marche pas ça !! je peux pas appeler des méthodes depuis une meme classe avec des propagation différentes au sein d'une meme classe.
    @Transactional(propagation = REQUIRED)
    public List<User> getUsers() {
        System.out.println("getUsers - active ? " + isActualTransactionActive());

        return userRepository.findAll();
    }

    @Transactional(propagation = REQUIRED)
    @Timed
    public void create(User user) {
        System.out.println("getUsers START - active ? " + isActualTransactionActive());

        userRepository.save(user);

        final UserCreatedEvent event = new UserCreatedEvent(user);

        applicationEventPublisher.publishEvent(event);

        System.out.println("getUsers END - active ? " + isActualTransactionActive());
    }



}
