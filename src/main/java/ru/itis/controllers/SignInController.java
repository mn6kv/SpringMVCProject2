package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.dto.UserForm;
import ru.itis.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class SignInController {

    @Autowired
    UserService userService;

    @GetMapping("/signIn")
    public String getSignIn(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "sign_in_view";
    }

    @PostMapping("/signIn")
    public String postSignIn(HttpServletRequest req,
                             HttpServletResponse resp,
                             @Valid UserForm userForm,
                             BindingResult bindingResult,
                             Model model) {
        if (userService.signIn(userForm)) {
            HttpServletRequest request;
            HttpSession session = req.getSession();
            session.setAttribute("userForm", userForm);
            return "redirect:/users";
        } else {
            model.addAttribute("userForm", userForm);
            return "sign_in_view";
        }
    }
}
