package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContextRepository extends JpaRepository<Context, Integer> {

    Context findByName(String name);


}
