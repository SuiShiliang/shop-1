package shop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.controller.form.OrderForm;
import shop.model.Order;
import shop.model.ShippingAddress;
import shop.model.ShoppingCart;
import shop.service.OrderService;
import shop.service.ShippingAddressService;
import shop.service.ShoppingCartService;

@Controller
public class OrderController {
    private ShoppingCartService shoppingCartService;
    
    private ShippingAddressService shippingAddressService;
    
    private OrderService orderService;
    
    @Autowired
    public OrderController(ShoppingCartService shoppingCartService,
                           ShippingAddressService shippingAddressService,
                           OrderService orderService) {
        this.shoppingCartService = shoppingCartService;
        this.shippingAddressService = shippingAddressService;
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/uc/orders/add")
    public String add(@AuthenticationPrincipal(expression = "user.id") Long userId,
                      Model model,
                      @ModelAttribute OrderForm orderForm) {
        prepareInAdd(userId, model);
        return "order-add";
    }

    private void prepareInAdd(Long userId, Model model) {
        ShoppingCart shoppingCart = shoppingCartService.findOneByUserId(userId);
        model.addAttribute("shoppingCart", shoppingCart);
        List<ShippingAddress> shippingAddresses = shippingAddressService.findAll(userId);
        model.addAttribute("shippingAddresses", shippingAddresses);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/uc/orders/add")
    public String create(@AuthenticationPrincipal(expression = "user.id") Long userId,
                         @ModelAttribute @Valid OrderForm orderForm,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            prepareInAdd(userId, model);
            return "order-add";
        }
        Order order = orderService.create(userId, orderForm.getShippingAddressId());
        return "redirect:/uc/orders/" + order.getId();
    }
}
