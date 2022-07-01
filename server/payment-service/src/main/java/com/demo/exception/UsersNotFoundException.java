package com.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UsersNotFoundException extends Exception {
    private String login;
}
