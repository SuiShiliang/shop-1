package shop.model;

public class OrderItem {
    private Long orderId;
    private Cellphone cellphone;
    private Integer amount;
    
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Cellphone getCellphone() {
        return cellphone;
    }
    public void setCellphone(Cellphone cellphone) {
        this.cellphone = cellphone;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
}
