package shop.model;

import java.util.List;

public class ShoppingCart {
    private List<ShoppingCartItem> items;

    public ShoppingCart(List<ShoppingCartItem> shoppingCartItems) {
        this.items = shoppingCartItems;
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    // TOOD 计算购物车总额
}
