package com.epam.demo.service;

import com.epam.demo.entity.User;
import com.epam.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    public User checkLoginAndPassword(String login, String password){
        return userRepository.findUserByLoginAndPassword(login,password);
    }

    public void addUsers(User user){ userRepository.save(user);
    }

    public User findUserByNumberCard(Long numberCard){
        return userRepository.findUserByNumberCard(numberCard);
    }

    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    public List<User> getUsersWhereBillBlocked(){
        return userRepository.getUsersWhereBillBlocked();
    }

    public User checkUsersByLogin(String login){
        return userRepository.findUserByLogin(login);
    }

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
}
