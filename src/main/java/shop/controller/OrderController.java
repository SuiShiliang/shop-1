package shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.controller.form.OrderForm;
import shop.model.ShippingAddress;
import shop.model.ShoppingCart;
import shop.service.ShippingAddressService;
import shop.service.ShoppingCartService;

@Controller
public class OrderController {
    private ShoppingCartService shoppingCartService;
    
    private ShippingAddressService shippingAddressService;
    
    @Autowired
    public OrderController(ShoppingCartService shoppingCartService,
                           ShippingAddressService shippingAddressService) {
        this.shoppingCartService = shoppingCartService;
        this.shippingAddressService = shippingAddressService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/uc/orders/add")
    public String add(@AuthenticationPrincipal(expression = "user.id") Long userId,
                      Model model,
                      @ModelAttribute OrderForm orderForm) {
        ShoppingCart shoppingCart = shoppingCartService.findOneByUserId(userId);
        model.addAttribute("shoppingCart", shoppingCart);
        List<ShippingAddress> shippingAddresses = shippingAddressService.findAll(userId);
        model.addAttribute("shippingAddresses", shippingAddresses);
        return "order-add";
    }
}
