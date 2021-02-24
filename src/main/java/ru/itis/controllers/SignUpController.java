package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.dto.UserForm;
import ru.itis.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    UserService userService;

    @GetMapping("/success")
    public String getSuccessPage() {
        return "check_mail_view";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public String getSignUpPage(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "sign_up_view";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(HttpServletRequest req,
                         HttpServletResponse resp,
                         @Valid UserForm userForm,
                         BindingResult bindingResult,
                         Model model) {
        if (!bindingResult.hasErrors()) {
            String sessionId = req.getSession().getId();

            Cookie cookie = new Cookie("sessionId", sessionId);
            cookie.setMaxAge(60 * 60 * 60);
            resp.addCookie(cookie);

            userService.addUser(userForm, sessionId);

            return "redirect:/success";
        }
        else {
            model.addAttribute("userForm", userForm);
            return "sign_up_view";
        }
    }
}
