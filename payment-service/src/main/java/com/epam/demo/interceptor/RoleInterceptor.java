package com.epam.demo.interceptor;

import com.epam.demo.entity.User;
import com.epam.demo.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoleInterceptor implements HandlerInterceptor {

    @Autowired
    private UserManager userManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        User currentUser = userManager.getUser();

        if (!currentUser.getRole().equals("admin")) {
            response.sendRedirect("/main");
            return false;
        }

        return true;

    }

}
