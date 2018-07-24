package shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shop.model.ShoppingCart;
import shop.model.ShoppingCartItem;
import shop.service.ShoppingCartService;

@Controller
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;
    
    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/uc/shopping-cart/add")
    public String add(@RequestParam Long cellphoneId,
                      @AuthenticationPrincipal(expression = "user.id") Long userId) {
        // 注意：SecurityConfig中配置的/uc/**需要登录保证了到达此控制器方法时，必定有用户已登录，这样才能顺利拿到userId
        shoppingCartService.addToCart(userId, cellphoneId, 1);
        return "redirect:/cellphones/" + cellphoneId;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/uc/shopping-cart")
    public String details(@AuthenticationPrincipal(expression = "user.id") Long userId,
                          Model model) {
        List<ShoppingCartItem> shoppingCartItems = shoppingCartService.findAllItems(userId);
        model.addAttribute("shoppingCart", new ShoppingCart(shoppingCartItems));
        return "shopping-cart";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/uc/shopping-cart/remove-item")
    public String removeItem(@AuthenticationPrincipal(expression = "user.id") Long userId,
                             @RequestParam Long cellphoneId) {
        shoppingCartService.removeItem(userId, cellphoneId);
        return "redirect:/uc/shopping-cart";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/uc/shopping-cart/item-dec")
    public String decItem(@AuthenticationPrincipal(expression = "user.id") Long userId,
                             @RequestParam Long cellphoneId) {
        shoppingCartService.decItem(userId, cellphoneId);
        return "redirect:/uc/shopping-cart";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/uc/shopping-cart/item-inc")
    public String incItem(@AuthenticationPrincipal(expression = "user.id") Long userId,
                             @RequestParam Long cellphoneId) {
        shoppingCartService.incItem(userId, cellphoneId);
        return "redirect:/uc/shopping-cart";
    }
}
