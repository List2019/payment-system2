package com.demo.service;

import com.demo.entity.User;

import java.util.List;

public interface IUserService {

    User checkLoginAndPassword(String login, String password);

    void addUsers(User user);

    List<User> getUsersWhereBillBlocked();

    List<User> getAllUsers();

    User findUserByNumberCard(Long numberCard);

    User checkUsersByLogin(String login);
}
