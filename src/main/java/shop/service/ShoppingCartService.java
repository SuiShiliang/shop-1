package shop.service;

import java.util.List;

import shop.model.ShoppingCartItem;

public interface ShoppingCartService {

    void addToCart(Long userId, Long cellphoneId, int amount);

    List<ShoppingCartItem> findAllItems(Long userId);

    void removeItem(Long userId, Long cellphoneId);

    void decItem(Long userId, Long cellphoneId);

    void incItem(Long userId, Long cellphoneId);

}
