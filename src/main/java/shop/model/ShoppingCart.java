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

    public int totalCost() {
        int result = 0;
        for (ShoppingCartItem item : items) {
            result += item.getCellphone().getPrice() * item.getAmount();
        }
        return result;
    }
}
