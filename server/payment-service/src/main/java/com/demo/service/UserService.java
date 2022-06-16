package com.demo.service;

import com.demo.entity.User;
import com.demo.repository.BankCardRepository;
import com.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService implements IUserService, UserDetailsService {

    private final UserRepository userRepository;
    private final BankCardRepository bankCardRepository;

    @Override
    public User checkLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public void addUsers(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByNumberCard(Long numberCard) {
        var cardNumber = bankCardRepository.findByCardNumber(numberCard);
        return userRepository.findByCardId(cardNumber.getId());
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public List<User> getUsersWhereBillBlocked() {
        return userRepository.getUsersWhereBillBlocked();
    }

    @Override
    public User checkUsersByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByName(username);
        if (Objects.nonNull(user)) {
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
