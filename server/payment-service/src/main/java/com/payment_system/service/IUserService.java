package com.payment_system.service;

import com.payment_system.model.entity.User;
import com.payment_system.model.exception.AccountNotFoundException;
import com.payment_system.model.exception.UsersNotFoundException;

import java.util.List;

//TODO implement user logic
public interface IUserService {

    User checkLoginAndPassword(String login, String password) throws UsersNotFoundException;

    void addUsers(User user);

    List<User> getUsersWhereBillBlocked();

    List<User> getAllUsers();

    User findUserByAccountNumber(String accountNumber) throws AccountNotFoundException;

    Boolean isUserExist(String login);
}
