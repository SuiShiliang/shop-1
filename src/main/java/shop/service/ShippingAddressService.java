package shop.service;

import java.util.List;

import shop.model.ShippingAddress;

public interface ShippingAddressService {

    List<ShippingAddress> findAll(Long userId);

    void create(ShippingAddress shippingAddress);

    ShippingAddress findOne(Long userId, Long shippingAddressId);

    void update(ShippingAddress shippingAddress);

}
