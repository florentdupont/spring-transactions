package com.example.demo;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.support.TransactionSynchronizationManager.isActualTransactionActive;

@Service

class TransactionalUserService2 {

        @Autowired
        UserRepository repository;

        @Transactional(readOnly = true)
        public List<User> getUsers() {
            System.out.println("TransactionalUserService2.getUsers - active ? " + isActualTransactionActive());
            return repository.findAll();
        }
    }