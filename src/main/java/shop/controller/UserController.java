package shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.controller.form.RegisterForm;

@Controller
public class UserController {
    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String register(@ModelAttribute RegisterForm registerForm) {
        return "register";
    }
}
