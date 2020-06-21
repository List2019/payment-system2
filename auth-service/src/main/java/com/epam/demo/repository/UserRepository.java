package com.epam.demo.repository;

import com.epam.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT users.* FROM epam.users, credit_card WHERE" +
            " users.id_users = credit_card.id_users AND credit_card.block = 1;", nativeQuery = true)
    List<User> getUsersWhereBillBlocked();
    User findUserByLoginAndPassword(String login, String password);
    User findUserByNumberCard(Long numberCard);
    User findUserByLogin(String login);

}
