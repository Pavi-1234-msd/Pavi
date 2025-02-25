package com.examly.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examly.springapp.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
}
