package com.demo.controller;

import com.demo.dto.TransferRequestDto;
import com.demo.entity.Account;
import com.demo.exception.AccountNotFoundException;
import com.demo.exception.MoneyNotEnoughException;
import com.demo.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * когда будет норм UI выпилить отсюда текст
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HomeController {

    private final AccountService accountService;

    private static final Logger logger = LogManager.getLogger(HomeController.class);


    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Token was validated successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/accounts")
    public ResponseEntity<String> createAccount(@RequestBody Account account) {
        var accountAlreadyExist = accountService.isAccountAlreadyExist(account.getNumber());
        if (Boolean.TRUE.equals(accountAlreadyExist)) {
            return new ResponseEntity<>("Карта с таким номером уже сущевствует", HttpStatus.CONFLICT);
        } else {
            accountService.addAccount(account);
            return new ResponseEntity<>("Карта успешно зарегистрирована", HttpStatus.CREATED);
        }
    }

    @PostMapping("/accounts/{accountNumber}")
    public ResponseEntity<String> deposit(@PathVariable String accountNumber, @RequestBody BigDecimal amount)
            throws AccountNotFoundException {
        var account = accountService.getAccountByNumber(accountNumber);

        if (account.isBlocked()) {
            return new ResponseEntity<>("К сожалению ваш счёт заблокирован", HttpStatus.FORBIDDEN);
        } else {
            accountService.deposit(amount, accountNumber);
            logger.info("Пополнение " + accountNumber + " на " + amount.intValue());
            return new ResponseEntity<>("Пополнение выполнен успешно," +
                    " ваш баланс: " + accountService.getBalance(accountNumber) + "", HttpStatus.OK);
        }
    }

    @PatchMapping("/accounts/{accountNumber}")
    public ResponseEntity<String> changeBlockStatus(@PathVariable String accountNumber, @RequestBody Boolean block) throws AccountNotFoundException {
        accountService.changeBlockingStatus(block, accountNumber);
        logger.info("Счет " + accountNumber + " заблокирован");
        return new ResponseEntity<>("Ваш счет успешно заблокированна", HttpStatus.OK);
    }

    @PostMapping("/accounts/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequestDto transferRequest) throws AccountNotFoundException,
            MoneyNotEnoughException {
        var from = accountService.getAccountByNumber(transferRequest.getFrom());
        var to = accountService.getAccountByNumber(transferRequest.getTo());
        BigDecimal amount = transferRequest.getAmount();

        if (from.isBlocked() || to.isBlocked()) {
            return new ResponseEntity<>("Один из счетов заблокирован", HttpStatus.CONFLICT);
        } else {
            accountService.simpleTransfer(amount, to, from);
            logger.info("Перевод от " + from.getNumber() + " к " + to.getNumber() + " на сумму " + amount.intValue());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
