package shop.mapper;

import org.apache.ibatis.annotations.Param;

import shop.model.Order;
import shop.model.OrderItem;

public interface OrderMapper {

    void create(Order order);

    void addItem(OrderItem orderItem);

    Order findOne(@Param("userId") Long userId, 
                  @Param("orderId") Long orderId);

}
