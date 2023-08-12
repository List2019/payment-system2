package com.payment_system.repository;

import com.payment_system.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    @Query(value = "SELECT users.* FROM users, account WHERE" +
            " users.id = account.number AND account.block = 1;", nativeQuery = true)
    List<User> getUsersWhereAccountBlocked();

    Optional<User> findByLoginAndPassword(String login, String password);

    Optional<User> findByLogin(String login);
}
