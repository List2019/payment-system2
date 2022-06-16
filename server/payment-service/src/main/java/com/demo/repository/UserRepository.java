package com.demo.repository;

import com.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT users.* FROM users, bank_card WHERE" +
            " users.id = bank_card.id AND bank_card.block = 1;", nativeQuery = true)
    List<User> getUsersWhereBillBlocked();

    User findByLoginAndPassword(String login, String password);

    User findByCardId(Long numberCard);

    User findByLogin(String login);

    User findByName(String name);
}
