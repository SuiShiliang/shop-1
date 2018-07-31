package shop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private Long userId;
    private ShippingAddress shippingAddress;
    private Date createdTime;
    
    private List<OrderItem> items = new ArrayList<>();
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }
    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    public Date getCreatedTime() {
        return createdTime;
    }
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    
    public List<OrderItem> getItems() {
        return items;
    }
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    
    public int totalCost() {
        int result = 0;
        for (OrderItem item : items) {
            result += item.getCellphone().getPrice() * item.getQuantity();
        }
        return result;
    }
}
