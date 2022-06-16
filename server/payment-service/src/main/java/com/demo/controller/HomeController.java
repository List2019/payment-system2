package com.demo.controller;

import com.demo.manager.CreditCardManager;
import com.demo.entity.BankCard;
import com.demo.entity.User;
import com.demo.manager.UserManager;
import com.demo.service.BankCardService;
import com.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CreditCardManager creditCardManager;
    private final BankCardService bankCardService;
    private final UserManager userManager;

    private static final Logger logger = LogManager.getLogger(HomeController.class);

    @RequestMapping("/main")
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @GetMapping("/createCard")
    public ModelAndView creditCardPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createCard");
        return modelAndView;
    }

    @GetMapping("/refill")
    public ModelAndView refillPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("refill");
        return modelAndView;
    }

    @GetMapping("/accountBlocking")
    public ModelAndView blockingPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("accountBlocking");
        return modelAndView;
    }

    @GetMapping("/transfer")
    public ModelAndView transferPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transfer");
        return modelAndView;
    }


    @PostMapping("/createCard")
    public ModelAndView createCard(BankCard bankCard, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errors", bindingResult.getAllErrors());
        } else {
            var cardByNumberCard = bankCardService.getBankCardByCardNumber(bankCard.getCardNumber());
            if (Objects.isNull(cardByNumberCard)) {
                User currentUser = userManager.getUser();
                bankCardService.add(currentUser, bankCard);
                modelAndView.addObject("message", "Карта успешно зарегистрирована");
            } else {
                modelAndView.addObject("message", "Карта с таким номером уже сущевствует");
            }
        }

        modelAndView.setViewName("createCard");
        return modelAndView;
    }

    @PostMapping("/refill")
    public ModelAndView refill(BigDecimal value) {
        ModelAndView modelAndView = new ModelAndView();

        BankCard currentBankCard = bankCardService.getBankCardByCardNumber(userManager.getUser().getCardId());

        if (currentBankCard.isBlocked()) {
            modelAndView.addObject("message", "К сожалению ваш счёт заблокирован");
        } else {
            bankCardService.deposit(value, userManager.getUser().getCardId());
            modelAndView.addObject("message", "Пополнение выполнен успешно," +
                    " ваш баланс: " + bankCardService.getBalance(currentBankCard.getCardNumber()) + "");
            logger.info("Пополнение " + currentBankCard.getCardNumber() + " на " + value.intValue());
        }

        modelAndView.setViewName("refill");
        return modelAndView;
    }

    @PostMapping("/accountBlocking")
    public ModelAndView blocking() {
        ModelAndView modelAndView = new ModelAndView();

        BankCard currentBankCard = bankCardService.getBankCardByCardNumber(userManager.getUser().getCardId());

        if (currentBankCard.isBlocked()) {
            modelAndView.addObject("message", "Ваша карта уже заблокированна");
        } else {
            bankCardService.block(userManager.getUser().getCardId());
            modelAndView.addObject("message", "Ваша карта успешно заблокированна");
            logger.info("Пользователь " + currentBankCard.getCardNumber() + " заблокировал счёт");
        }
        modelAndView.setViewName("accountBlocking");

        return modelAndView;

    }

    @PostMapping("/transfer")
    public ModelAndView transfer(BigDecimal value, long numberCard) {
        ModelAndView modelAndView = new ModelAndView();

        BankCard from = creditCardManager.getBankCard();
        BankCard to = bankCardService.getBankCardByCardNumber(numberCard);

        if (from.isBlocked()) {
            modelAndView.addObject("message", "К сожалению ваш счёт заблокирован");
            modelAndView.setViewName("transfer");
        } else {

            try {
                bankCardService.simpleTransfer(value, to, from);
                modelAndView.addObject("message", "Перевод выполнен успешно");
              //  log.info("Перевод от " + from.getCardNumber() + " к " + to.getCardNumber() + " на сумму " + value.intValue());


            } catch (EmptyResultDataAccessException ex) {
                modelAndView.addObject("message", "На вашем счету недостаточно средств");
            }

        }
        modelAndView.setViewName("transfer");
        return modelAndView;
    }
}
