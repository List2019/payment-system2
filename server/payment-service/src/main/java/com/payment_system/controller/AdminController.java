package com.payment_system.controller;

import com.payment_system.model.entity.Account;
import com.payment_system.model.entity.User;
import com.payment_system.model.exception.AccountNotFoundException;
import com.payment_system.service.IAccountService;
import com.payment_system.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final IAccountService accountService;
    private final IUserService userService;

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> findAllAccounts() {
        var accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/user/{accountNumber}")
    public ResponseEntity<User> search(@PathVariable String accountNumber) throws AccountNotFoundException {
        var user = userService.findUserByAccountNumber(accountNumber);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/account/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable String accountNumber) throws AccountNotFoundException {
        accountService.deleteAccount(accountNumber);
        return ResponseEntity.ok().build();
    }
}
