package com.demo.service;

import com.demo.entity.BankCard;
import com.demo.entity.User;
import com.demo.repository.BankCardRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class BankCardService implements IBankCardService {

    private final BankCardRepository bankCardRepository;

    public void deposit(BigDecimal value, long numberCard){
        var bankCard = bankCardRepository.findByCardNumber(numberCard);
        var result = bankCard.getBalance().add(value);
        bankCard.setBalance(result);
        bankCardRepository.save(bankCard);
    }

    public void withdraw(BigDecimal value, long numberCard){
        var bankCard = bankCardRepository.findByCardNumber(numberCard);
        var result = bankCard.getBalance().subtract(value);
        bankCard.setBalance(result);
        bankCardRepository.save(bankCard);
    }

    public BankCard getBankCardByCardNumber(long numberCard){
        return bankCardRepository.findByCardNumber(numberCard);
    }

    public double getBalance(long numberCard){
        return bankCardRepository.findByCardNumber(numberCard).getBalance().intValue();
    }

    public int getId(long numberCard){
        var bankCard =  bankCardRepository.findByCardNumber(numberCard);
        return Math.toIntExact(bankCard.getId());
    }

    public void deleteBankCard(long numberCard){
        var bankCard = bankCardRepository.findByCardNumber(numberCard);
        bankCardRepository.delete(bankCard);
    }

    public List<BankCard> getAllBankCards(){
        return (List<BankCard>) bankCardRepository.findAll();
    }

    public void add(User user, BankCard bankCard){
        bankCard.setId(user.getId());
        bankCardRepository.save(bankCard);
    }

    public void block(long numberCard){
        changeBlockingStatus(true, numberCard);
    }

    public void unblock(long numberCard){
        changeBlockingStatus(false, numberCard);
    }

    private void changeBlockingStatus(boolean block, long numberCard) {
        var bankCard = bankCardRepository.findByCardNumber(numberCard);
        bankCard.setBlocked(block);
        bankCardRepository.save(bankCard);
    }

    public void simpleTransfer(BigDecimal value, BankCard to, BankCard from){
        withdraw(value,from.getCardNumber());
        deposit(value,to.getCardNumber());
    }

    public boolean checkBalance(BigDecimal value, long numberCard){
        BankCard tempBankCard = bankCardRepository.findByCardNumber(numberCard);
        return tempBankCard.getBalance().compareTo(value) >= 0;
    }
}
