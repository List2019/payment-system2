package com.demo.controller;

import com.demo.entity.User;
import com.demo.manager.CreditCardManager;
import com.demo.manager.UserManager;
import com.demo.service.BankCardService;
import com.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final CreditCardManager creditCardManager;
    private final BankCardService creditCardService;
    private final UserService userService;
    private final UserManager userManager;

    @GetMapping("/login")
    @ResponseBody
    public String getLoginPage() {
        return "adfsdfs";
    }

    @GetMapping("/jwt")
    @ResponseBody
    public String jwtCheck() {
        return "adfsdfs";
    }

//    @GetMapping("/login")
//    public ModelAndView getLoginPage() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("login");
//        return modelAndView;
//    }

    @GetMapping("/registration")
    public ModelAndView registrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        request.getSession().invalidate();
        return modelAndView;
    }

    @ResponseBody
    @GetMapping("/checkLoginExist")
    public boolean checkLoginExist(String login) {
        return Objects.nonNull(userService.checkUsersByLogin(login));
    }

    @PostMapping("/login")
    public ModelAndView login(User user) {
        ModelAndView modelAndView = new ModelAndView();

        User currentUser = userService.checkLoginAndPassword(user.getLogin(), user.getPassword());

        if (currentUser != null) {
            userManager.setUser(currentUser);
            creditCardManager.setBankCard(creditCardService.getBankCardByCardNumber(currentUser.getCardId()));
            modelAndView.setViewName("redirect:/main");
        }

        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView registration(User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errors", bindingResult.getAllErrors());
            modelAndView.setViewName("registration");
        } else {
            userService.addUsers(user);
            userManager.setUser(user);
            modelAndView.setViewName("redirect:/main");
        }

        return modelAndView;
    }
}