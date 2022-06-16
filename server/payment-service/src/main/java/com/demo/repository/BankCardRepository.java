package com.demo.repository;

import com.demo.entity.BankCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCardRepository extends CrudRepository<BankCard, Long> {
    BankCard findByCardNumber(long cardNumber);
}
