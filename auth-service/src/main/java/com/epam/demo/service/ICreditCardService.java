package com.epam.demo.service;

import com.epam.demo.entity.CreditCard;
import com.epam.demo.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface ICreditCardService {

    int getIdByNumberCard(long numberCard);

    void deleteCardByNumber(Long numberCard);

    List<CreditCard> getAllCreditCard();

    void addCreditCard (User user, CreditCard creditCard);

    CreditCard getCardByNumberCard(long numberCard);

    void deposit(BigDecimal value, long numberCard);

    void blockCreditCardByNumberCard(long numberCard);

    void unblockCreditCardByNumberCard(long numberCard);

    boolean checkBalance(BigDecimal value, long numberCard);

    void withdraw(BigDecimal value, long numberCard);

    double getBalanceByNumberCard(long numberCard);

    void simpleTransfer(BigDecimal value, CreditCard to, CreditCard from);


}
