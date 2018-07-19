package shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.controller.form.RegisterForm;
import shop.service.UserService;

@Controller
public class UserController {
    
    private UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String register(@ModelAttribute RegisterForm registerForm) {
        return "register";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String create(@ModelAttribute @Valid RegisterForm registerForm,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        
        userService.register(registerForm.getUsername(), registerForm.getPassword());
        return "redirect:/login";
    }
}
