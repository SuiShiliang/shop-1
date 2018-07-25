package shop.mapper;

import java.util.List;

import shop.model.ShippingAddress;

public interface ShippingAddressMapper {

    List<ShippingAddress> findAll(Long userId);

    void create(ShippingAddress shippingAddress);

}
