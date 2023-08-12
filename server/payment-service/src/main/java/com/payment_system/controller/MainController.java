package com.payment_system.controller;

import com.payment_system.model.dto.TransferRequestDto;
import com.payment_system.model.entity.Account;
import com.payment_system.model.exception.AccountNotFoundException;
import com.payment_system.model.exception.NotEnoughMoneyException;
import com.payment_system.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MainController {

    private final AccountService accountService;
    private static final Logger logger = LogManager.getLogger(MainController.class);


    @GetMapping("/validateToken")
    public ResponseEntity<String> validateToken() {
        return new ResponseEntity<>("Token was validated successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/account")
    public ResponseEntity<String> createAccount(@RequestBody Account account) {
        var accountAlreadyExist = accountService.isAccountAlreadyExist(account.getAccountNumber());
        if (Boolean.TRUE.equals(accountAlreadyExist)) {
            return new ResponseEntity<>("Account with that number already exists", HttpStatus.CONFLICT);
        } else {
            accountService.addAccount(account);
            return new ResponseEntity<>("Account was successfully created", HttpStatus.CREATED);
        }
    }

    @PostMapping("/account/{accountNumber}")
    public ResponseEntity<String> deposit(@PathVariable String accountNumber, @RequestBody BigDecimal amount)
            throws AccountNotFoundException {
        var account = accountService.getAccountByNumber(accountNumber);

        if (account.isBlocked()) {
            return new ResponseEntity<>("Your account is blocked", HttpStatus.FORBIDDEN);
        } else {
            accountService.deposit(amount, accountNumber);
            logger.info("Deposit " + accountNumber + " for " + amount.intValue());
            return new ResponseEntity<>("Deposit was successful," +
                    " Your balance is: " + accountService.getBalance(accountNumber), HttpStatus.OK);
        }
    }

    @PatchMapping("/account/{accountNumber}")
    public ResponseEntity<String> changeBlockStatus(@PathVariable String accountNumber, @RequestBody Boolean block) throws AccountNotFoundException {
        accountService.changeBlockingStatus(block, accountNumber);
        logger.info("Account " + accountNumber + " successfully blocked");
        return new ResponseEntity<>("This account was successfully blocked", HttpStatus.OK);
    }

    @PostMapping("/account/{accountNumber}/transfer")
    public ResponseEntity<String> transfer(@PathVariable String accountNumber, @RequestBody TransferRequestDto transferRequest)
            throws AccountNotFoundException, NotEnoughMoneyException {
        Account from = accountService.getAccountByNumber(transferRequest.from());
        Account to = accountService.getAccountByNumber(transferRequest.to());
        BigDecimal amount = transferRequest.amount();

        if (from.isBlocked() || to.isBlocked()) {
            return new ResponseEntity<>("One of the accounts is blocked", HttpStatus.CONFLICT);
        } else {
            accountService.simpleTransfer(amount, to, from);
            logger.info("Transfer from " + accountNumber + " to " + to.getAccountNumber() + " , amount is " + amount.intValue());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
