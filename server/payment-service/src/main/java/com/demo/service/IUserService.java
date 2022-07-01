package com.demo.service;

import com.demo.entity.User;
import com.demo.exception.AccountNotFoundException;
import com.demo.exception.UsersNotFoundException;

import java.util.List;

public interface IUserService {

    User checkLoginAndPassword(String login, String password) throws UsersNotFoundException;

    void addUsers(User user);

    List<User> getUsersWhereBillBlocked();

    List<User> getAllUsers();

    User findUserByAccountNumber(String accountNumber) throws AccountNotFoundException;

    Boolean isUserExist(String login);
}
