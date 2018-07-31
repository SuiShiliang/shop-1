package shop.service;

import shop.model.ShoppingCart;

public interface ShoppingCartService {

    void addToCart(Long userId, Long cellphoneId, int quantity);

    void removeItem(Long userId, Long cellphoneId);

    ShoppingCart findOneByUserId(Long userId);

    void clearCart(Long userId);

    void updateItemQuantity(Long userId, Long cellphoneId, Integer quantity);

}
