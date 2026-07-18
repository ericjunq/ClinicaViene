package com.example.VieneClinica.security;

import com.example.VieneClinica.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserProvider {

    public User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
    }

    public Long getCurrentUserId(){
        return getCurrentUser().getId();
    }
}
