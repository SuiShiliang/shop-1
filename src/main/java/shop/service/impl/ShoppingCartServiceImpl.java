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
    public void addToCart(Long userId, Long cellphoneId, int amount) {
        Integer itemAmount = shoppingCartMapper.findItemAmount(userId, cellphoneId);
        if (itemAmount != null) { // 该项已存在
            if (itemAmount + amount == 0) { // 应用差量后若为0
                shoppingCartMapper.removeItem(userId, cellphoneId); // 删除该项
            } else {
                shoppingCartMapper.incItemAmount(userId, cellphoneId, amount);
            }
        } else {
            shoppingCartMapper.createItem(userId, cellphoneId, amount);
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
    public void decItem(Long userId, Long cellphoneId) {
        addToCart(userId, cellphoneId, -1);
    }

    @Override
    public void incItem(Long userId, Long cellphoneId) {
        addToCart(userId, cellphoneId, +1);
    }

    @Override
    public ShoppingCart findOneByUserId(Long userId) {
        return new ShoppingCart(findAllItems(userId));
    }

}
