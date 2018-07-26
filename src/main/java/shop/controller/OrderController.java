package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.model.ShoppingCart;
import shop.service.ShoppingCartService;

@Controller
public class OrderController {
    private ShoppingCartService shoppingCartService;
    
    @Autowired
    public OrderController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/uc/orders/add")
    public String add(@AuthenticationPrincipal(expression = "user.id") Long userId,
                      Model model) {
        ShoppingCart shoppingCart = shoppingCartService.findOneByUserId(userId);
        model.addAttribute("shoppingCart", shoppingCart);
        return "order-add";
    }
}
