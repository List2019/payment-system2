package com.demo.interceptor;

import com.demo.manager.UserManager;
import com.demo.entity.User;
import com.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserManager userManager;
    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        User currentUser = userManager.getUser();

        authService.getJwtToken();

        if (Objects.isNull(currentUser)) {
            response.sendRedirect("/login");
            return false;
        }

        return true;

    }

}
