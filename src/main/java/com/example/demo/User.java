package com.example.demo;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "t_user")
public class User {

    @Id
    @GeneratedValue
    public int id;

    @Column
    public String firstname;

    @Column
    public String lastname;

    @OneToMany
    public List<Context> contexts;


}
