package shop.service;

public interface ShoppingCartService {

    void addToCart(Long userId, Long cellphoneId, int amount);

}
