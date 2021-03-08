package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.dto.UserForm;
import ru.itis.models.User;
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
    public String getSignInPage(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "sign_in_view";
    }

    @PostMapping("/signIn")
    public String postSignIn(HttpServletRequest req,
                             HttpServletResponse resp,
                             Model model,
                             @Valid UserForm userForm,
                             BindingResult bindingResult) {
        User user = userService.signIn(userForm);
        if (user != null && !bindingResult.hasErrors()) {
            HttpServletRequest request;
            HttpSession session = req.getSession();
            return "users_view";
        } else {
            model.addAttribute("userForm", userForm);
            return "redirect:/signIn";
        }
    }
}
