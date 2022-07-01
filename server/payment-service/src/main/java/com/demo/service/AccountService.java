package com.demo.service;

import com.demo.entity.Account;
import com.demo.exception.AccountNotFoundException;
import com.demo.exception.MoneyNotEnoughException;
import com.demo.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    public void deposit(BigDecimal value, String accountNumber) throws AccountNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findById(accountNumber);

        if (optionalAccount.isPresent()) {
            var account = optionalAccount.get();
            var result = account.getBalance().add(value);
            account.setBalance(result);
            accountRepository.save(account);
        } else {
            throw new AccountNotFoundException(accountNumber);
        }
    }

    public void withdraw(BigDecimal value, String accountNumber) throws AccountNotFoundException, MoneyNotEnoughException {
        Optional<Account> optionalAccount = accountRepository.findById(accountNumber);

        if (optionalAccount.isPresent() && optionalAccount.get().getBalance().compareTo(value) >= 0) {
            var account = optionalAccount.get();
            var result = account.getBalance().subtract(value);
            account.setBalance(result);
            accountRepository.save(account);
        } else if (optionalAccount.isPresent() && optionalAccount.get().getBalance().compareTo(value) < 0) {
            throw new MoneyNotEnoughException();
        } else {
            throw new AccountNotFoundException(accountNumber);
        }
    }

    public Account getAccountByNumber(String accountNumber) throws AccountNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findById(accountNumber);

        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        } else {
            throw new AccountNotFoundException(accountNumber);
        }
    }

    public Boolean isAccountAlreadyExist(String accountNumber) {
        return accountRepository.findById(accountNumber).isPresent();
    }

    public BigDecimal getBalance(String accountNumber) throws AccountNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findById(accountNumber);

        if (optionalAccount.isPresent()) {
            var account = optionalAccount.get();
            return account.getBalance();
        } else {
            throw new AccountNotFoundException(accountNumber);
        }
    }

    public void deleteAccount(String accountNumber) throws AccountNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findById(accountNumber);

        if (optionalAccount.isPresent()) {
            var account = optionalAccount.get();
            accountRepository.delete(account);
        } else {
            throw new AccountNotFoundException(accountNumber);
        }
    }

    public List<Account> getAllAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    public void changeBlockingStatus(boolean block, String accountNumber) throws AccountNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findById(accountNumber);

        if (optionalAccount.isPresent()) {
            var account = optionalAccount.get();
            account.setBlocked(block);
            accountRepository.save(account);
        } else {
            throw new AccountNotFoundException(accountNumber);
        }
    }

    public void simpleTransfer(BigDecimal value, Account to, Account from) throws AccountNotFoundException, MoneyNotEnoughException {
        withdraw(value, from.getNumber());
        deposit(value, to.getNumber());
    }

    public boolean checkBalance(BigDecimal value, String accountNumber) throws AccountNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findById(accountNumber);

        if (optionalAccount.isPresent()) {
            var account = optionalAccount.get();
            return account.getBalance().compareTo(value) >= 0;
        } else {
            throw new AccountNotFoundException(accountNumber);
        }
    }
}
