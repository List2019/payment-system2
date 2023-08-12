package com.payment_system.service;

import com.payment_system.model.entity.Account;
import com.payment_system.model.entity.User;
import com.payment_system.model.exception.AccountNotFoundException;
import com.payment_system.model.exception.UsersNotFoundException;
import com.payment_system.repository.AccountRepository;
import com.payment_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService, UserDetailsService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Override
    public User checkLoginAndPassword(String login, String password) throws UsersNotFoundException {
        Optional<User> optionalUser = userRepository.findByLoginAndPassword(login, password);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UsersNotFoundException(login);
        }
    }

    @Override
    public void addUsers(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByAccountNumber(String accountNumber) throws AccountNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findById(accountNumber);

        if (optionalAccount.isPresent()) {
            return optionalAccount.get().getUser();
        } else {
            throw new AccountNotFoundException(accountNumber);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public List<User> getUsersWhereBillBlocked() {
        return userRepository.getUsersWhereAccountBlocked();
    }

    @Override
    public Boolean isUserExist(String login) {
        return userRepository.findByLogin(login).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optionalUser = userRepository.findByLogin(username);
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
