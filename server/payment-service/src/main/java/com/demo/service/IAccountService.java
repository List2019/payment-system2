package com.demo.service;

import com.demo.entity.Account;
import com.demo.exception.AccountNotFoundException;
import com.demo.exception.MoneyNotEnoughException;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountService {
    List<Account> getAllAccounts();

    void addAccount(Account account);

    Account getAccountByNumber(String accountNumber) throws AccountNotFoundException;

    void deposit(BigDecimal value, String accountNumber) throws AccountNotFoundException;

    void changeBlockingStatus(boolean block, String accountNumber) throws AccountNotFoundException;

    boolean checkBalance(BigDecimal value, String accountNumber) throws AccountNotFoundException;

    void withdraw(BigDecimal value, String accountNumber) throws AccountNotFoundException, MoneyNotEnoughException;

    BigDecimal getBalance(String accountNumber) throws AccountNotFoundException;

    Boolean isAccountAlreadyExist(String accountNumber);

    void deleteAccount(String accountNumber) throws AccountNotFoundException;

    void simpleTransfer(BigDecimal value, Account to, Account from) throws AccountNotFoundException, MoneyNotEnoughException;
}
