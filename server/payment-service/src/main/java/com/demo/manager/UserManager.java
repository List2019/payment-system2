package com.demo.manager;

import com.demo.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
@Setter
@Getter
public class UserManager {
    private User user;
}
