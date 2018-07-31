package shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mapper.ShoppingCartMapper;
import shop.model.ShoppingCart;
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
    public void addToCart(Long userId, Long cellphoneId, int quantity) {
        Integer itemQuantity = shoppingCartMapper.findItemQuantity(userId, cellphoneId);
        if (itemQuantity != null) { // 该项已存在
            shoppingCartMapper.updateItemQuantity(userId, cellphoneId, itemQuantity + quantity);
        } else {
            shoppingCartMapper.createItem(userId, cellphoneId, quantity);
        }
    }

    private List<ShoppingCartItem> findAllItems(Long userId) {
        return shoppingCartMapper.findAllItems(userId);
    }

    @Override
    public void removeItem(Long userId, Long cellphoneId) {
        shoppingCartMapper.removeItem(userId, cellphoneId);
    }

    @Override
    public ShoppingCart findOneByUserId(Long userId) {
        return new ShoppingCart(findAllItems(userId));
    }

    @Override
    public void clearCart(Long userId) {
        shoppingCartMapper.deleteItemsByUserId(userId);
    }

    @Override
    public void updateItemQuantity(Long userId, Long cellphoneId, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("购物车项的数量必须大于0");
        }
        shoppingCartMapper.updateItemQuantity(userId, cellphoneId, quantity);
    }

}
