package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.services.UserService;

@Controller
public class ConfirmController {

    @Autowired
    UserService userService;

    @GetMapping("/confirm/{code}")
    public String confirmUser(@PathVariable("code") String code, Model model) {
        userService.confirmUserWithCode(code);
        return "success_auth_view";
    }
}
