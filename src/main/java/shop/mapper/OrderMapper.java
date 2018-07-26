package shop.mapper;

import shop.model.Order;
import shop.model.OrderItem;

public interface OrderMapper {

    void create(Order order);

    void addItem(OrderItem orderItem);

}
