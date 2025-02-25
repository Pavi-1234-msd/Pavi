package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.entities.User;
import com.examly.springapp.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) 
    {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() 
    {
        return userRepository.findAll();
    }

    public User getUserById(Long id) 
    {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) 
    {
        return userRepository.save(user);
    }

    public User updateUser(User user) 
    {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) 
        {
            return userRepository.save(user);
        } else 
        {
            return null;
        }
    }

    public void deleteUser(Long id) 
    {
        userRepository.deleteById(id);
    }
}
