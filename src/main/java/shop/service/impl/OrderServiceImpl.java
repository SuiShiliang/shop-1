package shop.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mapper.OrderMapper;
import shop.model.Order;
import shop.model.OrderItem;
import shop.model.OrderState;
import shop.model.ShippingAddress;
import shop.model.ShoppingCart;
import shop.model.ShoppingCartItem;
import shop.service.OrderService;
import shop.service.ShoppingCartService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private OrderMapper orderMapper;
    
    private ShoppingCartService shoppingCartService;
    
    private AlipayClient alipayClient;
    
    private String alipayReturnUrl;
    private String alipayNotifyUrl;
    
    private ObjectMapper objectMapper;
    
    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper,
                            ShoppingCartService shoppingCartService, 
                            AlipayClient alipayClient,
                            Environment env,
                            ObjectMapper objectMapper) {
        this.orderMapper = orderMapper;
        this.shoppingCartService = shoppingCartService;
        this.alipayClient = alipayClient;
        this.alipayReturnUrl = env.getProperty("alipay.returnUrl");
        this.alipayNotifyUrl = env.getProperty("alipay.notifyUrl");
        this.objectMapper = objectMapper;
    }

    @Override
    public Order create(Long userId, Long shippingAddressId) {
        // 订单表
        Order order = new Order();
        order.setUserId(userId);
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setId(shippingAddressId);
        order.setShippingAddress(shippingAddress);
        order.setCreatedTime(new Date());
        order.setState(OrderState.Created);
        
        orderMapper.create(order);
        
        // 订单项表
        ShoppingCart shoppingCart = shoppingCartService.findOneByUserId(userId);
        for (ShoppingCartItem cartItem : shoppingCart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setCellphone(cartItem.getCellphone());
            orderItem.setQuantity(cartItem.getQuantity());
            orderMapper.addItem(orderItem);
        }
        
        // 清空购物车
        shoppingCartService.clearCart(userId);
        
        return order;
    }

    @Override
    public Order findOne(Long userId, Long orderId) {
        return orderMapper.findOne(userId, orderId);
    }

    @Override
    public List<Order> findAll(Long userId) {
        return orderMapper.findAll(userId);
    }

    @Override
    public String payForm(Long userId, Long id) {
        Order order = findOne(userId, id);
        
        if (order.getState() != OrderState.Created) {
            throw new IllegalStateException("只有Created状态的订单才能发起支付");
        }
        
        BigDecimal totalAmount = BigDecimal.valueOf(order.totalCost()).divide(BigDecimal.valueOf(100)); // 订单总金额（元）
        
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest(); // 即将发送给支付宝的请求（电脑网站支付请求）
        alipayRequest.setReturnUrl(alipayReturnUrl); // 浏览器端完成支付后跳转回商户的地址（同步通知）
        alipayRequest.setNotifyUrl(alipayNotifyUrl); // 支付宝服务端确认支付成功后通知商户的地址（异步通知）
        
        Map<String, Object> bizContent = new HashMap<>(); // biz - business
        // 填充业务参数
        bizContent.put("out_trade_no", "" + id + "-" + new Date().getTime()); // 商户订单号，加时间戳是为了避免测试时订单号重复
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY"); // 产品码，固定
        bizContent.put("total_amount", totalAmount); // 订单总金额（元）
        bizContent.put("subject", "shop手机商城订单支付"); // 订单标题
        bizContent.put("body", "TODO 显示订单项概要"); // 订单描述
        
        // 直接将完整的表单html输出到页面
        try {
            String bizContentStr = objectMapper.writeValueAsString(bizContent);
            System.out.println("alipay.bizContentStr: " + bizContentStr);
            alipayRequest.setBizContent(bizContentStr);
            return alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成支付表单
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
    }

}
