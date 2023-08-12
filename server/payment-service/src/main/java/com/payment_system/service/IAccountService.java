package com.payment_system.service;

import com.payment_system.model.entity.Account;
import com.payment_system.model.exception.AccountNotFoundException;
import com.payment_system.model.exception.NotEnoughMoneyException;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountService {
    List<Account> getAllAccounts();

    void addAccount(Account account);

    Account getAccountByNumber(String accountNumber) throws AccountNotFoundException;

    void deposit(BigDecimal value, String accountNumber) throws AccountNotFoundException;

    void changeBlockingStatus(boolean block, String accountNumber) throws AccountNotFoundException;

    void withdraw(BigDecimal value, String accountNumber) throws AccountNotFoundException, NotEnoughMoneyException;

    BigDecimal getBalance(String accountNumber) throws AccountNotFoundException;

    Boolean isAccountAlreadyExist(String accountNumber);

    void deleteAccount(String accountNumber) throws AccountNotFoundException;

    void simpleTransfer(BigDecimal value, Account to, Account from) throws AccountNotFoundException, NotEnoughMoneyException;
}
