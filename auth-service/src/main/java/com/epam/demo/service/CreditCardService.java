package com.epam.demo.service;

import com.epam.demo.entity.CreditCard;
import com.epam.demo.entity.User;
import com.epam.demo.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CreditCardService implements ICreditCardService{

    private CreditCardRepository creditCardRepository;

    public void deposit(BigDecimal value, long numberCard){
        CreditCard currentCard = creditCardRepository.findCreditCardByNumberCard(numberCard);
        BigDecimal result = currentCard.getBalance().add(value);
        creditCardRepository.setBalance(result.intValue(),numberCard);
    }

    public void withdraw(BigDecimal value, long numberCard){
        CreditCard currentCard = creditCardRepository.findCreditCardByNumberCard(numberCard);
        BigDecimal result = currentCard.getBalance().subtract(value);
        creditCardRepository.setBalance(result.intValue(),numberCard);
    }

    public CreditCard getCardByNumberCard(long numberCard){
        return creditCardRepository.findCreditCardByNumberCard(numberCard);
    }

    public double getBalanceByNumberCard(long numberCard){
        return creditCardRepository.findCreditCardByNumberCard(numberCard).getBalance().intValue();
    }

    public int getIdByNumberCard(long numberCard){
        CreditCard tempCreditCard =  creditCardRepository.findCreditCardByNumberCard(numberCard);
        return Math.toIntExact(tempCreditCard.getIdUsers());
    }

    public void deleteCardByNumber(Long numberCard){
        creditCardRepository.deleteCardByNumberCard(numberCard);
    }

    public List<CreditCard> getAllCreditCard(){
        return (List<CreditCard>) creditCardRepository.findAll();
    }

    public void addCreditCard(User user, CreditCard creditCard){
        creditCard.setIdUsers(user.getIdUsers());
        creditCardRepository.save(creditCard);
    }

    public void blockCreditCardByNumberCard(long numberCard){
        creditCardRepository.setBlock(true, numberCard);
    }

    public void unblockCreditCardByNumberCard(long numberCard){
        creditCardRepository.setBlock(false, numberCard);
    }

    public void simpleTransfer(BigDecimal value, CreditCard to, CreditCard from){
        withdraw(value,from.getNumberCard());
        deposit(value,to.getNumberCard());
    }

    public boolean checkBalance(BigDecimal value, long numberCard){
        CreditCard tempCreditCard = creditCardRepository.findCreditCardByNumberCard(numberCard);
        return tempCreditCard.getBalance().compareTo(value) >= 0;
    }

    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository){
        this.creditCardRepository = creditCardRepository;
    }
}
