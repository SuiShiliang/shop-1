package shop.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShoppingCart {
    private List<ShoppingCartItem> items;

    public ShoppingCart(List<ShoppingCartItem> shoppingCartItems) {
        this.items = shoppingCartItems;
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    @JsonProperty("totalCost") // 调用该方法将返回值以totalCost为名写入json
    public int totalCost() {
        int result = 0;
        for (ShoppingCartItem item : items) {
            result += item.getCellphone().getPrice() * item.getQuantity();
        }
        return result;
    }

}
