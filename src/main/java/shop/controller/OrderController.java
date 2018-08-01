package shop.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import shop.controller.form.OrderForm;
import shop.model.Order;
import shop.model.OrderState;
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
    
    private AlipayClient alipayClient;
    
    @Autowired
    public OrderController(ShoppingCartService shoppingCartService,
                           ShippingAddressService shippingAddressService,
                           OrderService orderService, AlipayClient alipayClient) {
        this.shoppingCartService = shoppingCartService;
        this.shippingAddressService = shippingAddressService;
        this.orderService = orderService;
        this.alipayClient = alipayClient;
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
    
    @RequestMapping(method = RequestMethod.GET, value = "/uc/orders/{id}")
    public String details(@AuthenticationPrincipal(expression = "user.id") Long userId,
                          @PathVariable Long id,
                          Model model) {
        Order order = orderService.findOne(userId, id);
        model.addAttribute("order", order);
        return "order-details";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/uc/orders/")
    public String list(@AuthenticationPrincipal(expression = "user.id") Long userId,
                       Model model) {
        List<Order> orders = orderService.findAll(userId);
        model.addAttribute("orders", orders);
        return "order-list";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/uc/orders/{id}/pay", 
                    produces = "text/html;charset=UTF-8" // 非常重要，支付宝api响应是html片段（不含编码），必须显式指定
                    )
    @ResponseBody
    public String pay(@AuthenticationPrincipal(expression = "user.id") Long userId,
                      @PathVariable Long id) throws AlipayApiException {
        Order order = orderService.findOne(userId, id);
        
        if (order.getState() != OrderState.Created) {
            throw new IllegalStateException("只有Created状态的订单才能发起支付");
        }
        
        BigDecimal totalAmount = BigDecimal.valueOf(order.totalCost()).divide(BigDecimal.valueOf(100)); // 订单总金额（元）
        
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest(); // 即将发送给支付宝的请求（电脑网站支付请求）
        alipayRequest.setReturnUrl("http://shop.me/shop/uc/orders/sync-pay-cb"); // 浏览器端完成支付后跳转回商户的地址（同步通知）
        alipayRequest.setNotifyUrl("http://shop.me/shop/async-pay-cb"); // 支付宝服务端确认支付成功后通知商户的地址（异步通知）
        alipayRequest.setBizContent("{" +
            "    \"out_trade_no\":\"" + id.toString() + "-" + new Date().getTime() + "\"," + // 商户订单号，加时间戳是为了避免测试时订单号重复
            "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," + // 产品码，固定
            "    \"total_amount\":" + totalAmount.toString() + "," + // 订单总金额（元）
            "    \"subject\":\"shop手机商城订单支付\"," + // 订单标题
            "    \"body\":\"TODO 显示订单项概要\"" + // 订单描述
            "  }"); // 填充业务参数
        
        // 直接将完整的表单html输出到页面
        return alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成支付表单
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/uc/orders/sync-pay-cb")
    public String payOk(@RequestParam("out_trade_no") String orderNumber,
                        Model model) {
        Long orderId = Long.valueOf(orderNumber.split("-")[0]); // 如 3-1533093080374
        model.addAttribute("orderId", orderId);
        return "order-pay-ok";
    }
}
