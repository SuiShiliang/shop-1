package shop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private Long userId;
    private ShippingAddress shippingAddress;
    private Date createdTime;
    
    private OrderState state;
    private Integer totalAmount;
    
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
    
    public OrderState getState() {
        return state;
    }
    public void setState(OrderState state) {
        this.state = state;
    }
    
    public Integer getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public int totalCost() {
        int result = 0;
        for (OrderItem item : items) {
            result += item.getCellphone().getPrice() * item.getQuantity();
        }
        return result;
    }
    
    public String stateText() {
        switch (state) {
        case Created:
            return "待支付";
            
        case Paid:
            return "待发货";
            
        case Shipped:
            return "已发货";
            
        case Delivered:
            return "已送达";
            
        case Commented:
            return "已评论";
            
        case Canceled:
            return "已取消";

        default:
            return "?" + state;
        }
    }
}
