package shop.service;

import java.util.List;

import shop.model.Order;

public interface OrderService {

    Order create(Long userId, Long shippingAddressId);

    Order findOne(Long userId, Long id);

    List<Order> findAll(Long userId);

    String payForm(Long userId, Long id);

}
