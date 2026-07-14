package com.example.VieneClinica.repository;

import com.example.VieneClinica.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
