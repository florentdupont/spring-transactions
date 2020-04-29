package com.example.demo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Data
public class UserDto {

    public int id;

    public String firstname;

    public String lastname;

    public List<String> contextNames;


}
