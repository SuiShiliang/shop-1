package shop.model;

public class ShoppingCartItem {
    private Cellphone cellphone;
    private int quantity;
    
    public Cellphone getCellphone() {
        return cellphone;
    }
    public void setCellphone(Cellphone cellphone) {
        this.cellphone = cellphone;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
