package com.example.VieneClinica.mapper;

import com.example.VieneClinica.entity.Person;
import com.example.VieneClinica.entity.User;
import com.example.VieneClinica.enums.StatusAtividade;

public class UserMapper {

    public User toUser(Person person, StatusAtividade statusAtividade){
        User user = new User();
        user.setPerson(person);
        user.setStatus(statusAtividade);
        return user;
    }
}
