package shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mapper.ShoppingCartMapper;
import shop.model.ShoppingCartItem;
import shop.service.ShoppingCartService;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCartMapper shoppingCartMapper;
    
    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @Override
    public void addToCart(Long userId, Long cellphoneId, int amount) {
        if (shoppingCartMapper.itemExists(userId, cellphoneId)) {
            shoppingCartMapper.incItemAmount(userId, cellphoneId, amount);
        } else {
            shoppingCartMapper.createItem(userId, cellphoneId, amount);
        }
    }

    @Override
    public List<ShoppingCartItem> findAllItems(Long userId) {
        return shoppingCartMapper.findAllItems(userId);
    }

    @Override
    public void removeItem(Long userId, Long cellphoneId) {
        shoppingCartMapper.removeItem(userId, cellphoneId);
    }

}
