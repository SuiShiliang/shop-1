package shop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mapper.OrderMapper;
import shop.model.Order;
import shop.model.OrderItem;
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
    
    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper,
                            ShoppingCartService shoppingCartService) {
        this.orderMapper = orderMapper;
        this.shoppingCartService = shoppingCartService;
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

}
