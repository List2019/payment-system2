package com.demo.manager;

import com.demo.entity.BankCard;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class CreditCardManager {
    @Getter
    @Setter
    private BankCard bankCard;
}
