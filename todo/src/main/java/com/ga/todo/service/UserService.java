package com.ga.todo.service;

import com.ga.todo.exception.InformationExistException;
import com.ga.todo.model.User;
import com.ga.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User userObject){
        System.out.println("Service calling createUser ==> ");
        if(!userRepository.existsByEmailAddress(userObject.getEmailAddress())){
            userObject.setPassword(userObject.getPassword());
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("User with email address " + userObject.getEmailAddress() + " already exists.");

        }
    }
}
