package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import static com.example.demo.TimingUtils.tooLongOperation;
import static org.springframework.transaction.support.TransactionSynchronizationManager.isActualTransactionActive;

@Slf4j
@Component
public class UserCreatedEventListener {

//    private final TokenGenerator tokenGenerator;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;
//
    public UserCreatedEventListener() {
    }

    @EventListener
    @Async
    public void processUserCreatedEvent(UserCreatedEvent event) {

        log.info("Event received: " + event);
        System.out.println("processUserCreatedEvent - active ? " + isActualTransactionActive());

        // a faire dans resilience4j via un Retry, si ca faile...je fais quoi ?
        tooLongOperation();

        System.out.println("pool size : " + threadPoolTaskExecutor.getPoolSize());
        System.out.println("max pool size : " + threadPoolTaskExecutor.getMaxPoolSize());
        System.out.println("thread prefix : " + threadPoolTaskExecutor.getThreadNamePrefix());

        // tokenGenerator.generateToken(event.getCustomer());

        // ici je peux faire autre chose... réouvrir une connexion, changer le statut de l'utilisateur, etc..

        log.info("Event finished: " + event);

    }

}