package com.demo.service;

import com.demo.entity.BankCard;
import com.demo.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface IBankCardService {
    List<BankCard> getAllBankCards();

    void add(User user, BankCard bankCard);

    BankCard getBankCardByCardNumber(long numberCard);

    void deposit(BigDecimal value, long numberCard);

    void block(long numberCard);

    void unblock(long numberCard);

    boolean checkBalance(BigDecimal value, long numberCard);

    void withdraw(BigDecimal value, long numberCard);

    double getBalance(long numberCard);

    void simpleTransfer(BigDecimal value, BankCard to, BankCard from);


}
