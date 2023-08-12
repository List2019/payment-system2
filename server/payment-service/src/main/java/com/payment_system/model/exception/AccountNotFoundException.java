package com.payment_system.model.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountNotFoundException extends Exception {
    private String accountNumber;
}
