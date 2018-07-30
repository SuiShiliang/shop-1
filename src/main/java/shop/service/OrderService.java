package shop.service;

import shop.model.Order;

public interface OrderService {

    Order create(Long userId, Long shippingAddressId);

    Order findOne(Long userId, Long id);

}
