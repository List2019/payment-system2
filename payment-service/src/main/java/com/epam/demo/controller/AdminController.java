package com.epam.demo.controller;

import com.epam.demo.entity.CreditCard;
import com.epam.demo.entity.User;
import com.epam.demo.manager.CreditCardManager;
import com.epam.demo.manager.UserManager;
import com.epam.demo.service.CreditCardService;
import com.epam.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class AdminController {

    private CreditCardManager creditCardManager;
    private CreditCardService creditCardService;
    private UserManager userManager;
    private UserService userService;

    @GetMapping("payment-service/search")
    public ModelAndView searchByNumberCard() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        return modelAndView;
    }

    @GetMapping("payment-service/admin")
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @GetMapping("payment-service/allCard")
    public ModelAndView allCardPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<CreditCard> cards = creditCardService.getAllCreditCard();
        modelAndView.addObject("CreditCard", new CreditCard());
        modelAndView.addObject("cards", cards);
        modelAndView.setViewName("allCard");
        return modelAndView;
    }

    @PostMapping("payment-service/search")
    public ModelAndView search(Long numberCard) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserByNumberCard(numberCard);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("search");
        return modelAndView;
    }

    @PostMapping("payment-service/allCard")
    public ModelAndView allCard(@ModelAttribute("NumberCard") CreditCard creditCard) {

        ModelAndView modelAndView = new ModelAndView();
        creditCardService.deleteCardByNumber(creditCard.getNumberCard());
        modelAndView.setViewName("redirect:allCard");

        return modelAndView;
    }


    @GetMapping("payment-service/unblocking")
    public ModelAndView unblockPage() {

        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.getUsersWhereBillBlocked();
        modelAndView.addObject("CreditCard", new CreditCard());
        modelAndView.addObject("users", users);
        modelAndView.setViewName("unblocking");

        return modelAndView;
    }

    @PostMapping("payment-service/unblocking")
    public ModelAndView unblockingPage(@ModelAttribute("NumberCard") CreditCard creditCard) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            creditCardService.unblockCreditCardByNumberCard(creditCard.getNumberCard());
            modelAndView.setViewName("redirect:unblocking");
        } catch (Exception ex) {
            modelAndView.addObject("message", "чё то пошло не так");
            modelAndView.setViewName("unblocking");
        }
        return modelAndView;
    }

    @RequestMapping(value = "payment-service/admin/allUsers/{pageId}", method = RequestMethod.GET)
    public ModelAndView allUsersPage(@PathVariable int pageId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        PagedListHolder<User> userList;
        if (pageId == 1) {
            userList = new PagedListHolder<User>();
            List<User> usersList = userService.getAllUsers();
            // Setting the source for PagedListHolder
            userList.setSource(usersList);
            userList.setPageSize(4);
            // Setting PagedListHolder instance to session
            request.getSession().setAttribute("userList", userList);
        } else {
            userList = (PagedListHolder<User>) request.getSession().getAttribute("userList");
            // set the current page number
            // page number starts from zero in PagedListHolder that's why subtracting 1
            userList.setPage(pageId - 1);
        }
        modelAndView.setViewName("allUsers");
        return modelAndView;
    }

    @RequestMapping("payment-service/admin/log")
    public ModelAndView logPage() {
        ModelAndView modelAndView = new ModelAndView();
        try (FileReader fr = new FileReader("log.log")) {
            Scanner scan = new Scanner(fr);
            int i = 1;
            ArrayList<String> logs = new ArrayList<>();

            while (scan.hasNextLine()) {
                logs.add(i + ": " + scan.nextLine());
                i++;
            }
            modelAndView.addObject("logs", logs);
        } catch (IOException e) {
            modelAndView.addObject("message", "Файл логирования не найден");
        }
        modelAndView.setViewName("log");
        return modelAndView;
    }

    @Autowired
    public AdminController(CreditCardManager creditCardManager, CreditCardService creditCardService,
                           UserManager userManager, UserService userService) {
        this.creditCardService = creditCardService;
        this.creditCardManager = creditCardManager;
        this.userManager = userManager;
        this.userService = userService;
    }
}
