package com.example.demo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "t_context")
public class Context {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

}
