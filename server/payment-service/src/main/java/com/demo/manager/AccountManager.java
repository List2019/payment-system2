package com.demo.manager;

import com.demo.entity.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class AccountManager {
    @Getter
    @Setter
    private Account account;
}
