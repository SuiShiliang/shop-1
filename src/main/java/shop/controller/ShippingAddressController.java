package shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.model.ShippingAddress;
import shop.service.ShippingAddressService;

@Controller
public class ShippingAddressController {
    private ShippingAddressService shippingAddressService;
    
    @Autowired
    public ShippingAddressController(ShippingAddressService shippingAddressService) {
        this.shippingAddressService = shippingAddressService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/uc/shipping-addresses/")
    public String list(Model model) {
        List<ShippingAddress> shippingAddresses = shippingAddressService.findAll();
        model.addAttribute("shippingAddresses", shippingAddresses);
        return "shipping-address-list";
    }
}
