package shop.model;

public class ShoppingCartItem {
    private Cellphone cellphone;
    private int amount;
    
    public Cellphone getCellphone() {
        return cellphone;
    }
    public void setCellphone(Cellphone cellphone) {
        this.cellphone = cellphone;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
