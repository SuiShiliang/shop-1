package shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shop.model.ShippingAddress;

public interface ShippingAddressMapper {

    List<ShippingAddress> findAll(Long userId);

    void create(ShippingAddress shippingAddress);

    ShippingAddress findOne(@Param("userId") Long userId, 
                            @Param("shippingAddressId") Long shippingAddressId);

    void update(ShippingAddress shippingAddress);

}
