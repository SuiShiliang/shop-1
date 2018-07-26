package shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mapper.ShippingAddressMapper;
import shop.model.ShippingAddress;
import shop.service.ShippingAddressService;

@Service
@Transactional
public class ShippingAddressServiceImpl implements ShippingAddressService {
    private ShippingAddressMapper shippingAddressMapper;
    
    @Autowired
    public ShippingAddressServiceImpl(ShippingAddressMapper shippingAddressMapper) {
        this.shippingAddressMapper = shippingAddressMapper;
    }

    @Override
    public List<ShippingAddress> findAll(Long userId) {
        return shippingAddressMapper.findAll(userId);
    }

    @Override
    public void create(ShippingAddress shippingAddress) {
        shippingAddressMapper.create(shippingAddress);
    }

    @Override
    public ShippingAddress findOne(Long userId, Long shippingAddressId) {
        return shippingAddressMapper.findOne(userId, shippingAddressId);
    }

    @Override
    public void update(ShippingAddress shippingAddress) {
        shippingAddressMapper.update(shippingAddress);
    }

}
