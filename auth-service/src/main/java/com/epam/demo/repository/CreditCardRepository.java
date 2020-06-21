package com.epam.demo.repository;

import com.epam.demo.entity.CreditCard;
import com.epam.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE epam.credit_card SET balance = ?1 WHERE (number_card = ?2)", nativeQuery = true)
    void setBalance(int value, long numberCard);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE epam.credit_card SET block = ?1 WHERE (number_card = ?2)", nativeQuery = true)
    void setBlock(boolean block, long numberCard);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    void deleteCardByNumberCard(long numberCard);

    CreditCard findCreditCardByNumberCard(long numberCard);

}
