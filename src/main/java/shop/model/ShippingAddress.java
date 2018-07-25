package shop.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ShippingAddress {
    private Long id;
    
    @Size(min = 1, max = 64, message = "1~64字")
    private String name;
    
    @Pattern(regexp = "1[0-9]{10}", message = "请输入11位手机号")
    private String phoneNumber;
    
    @Size(min = 3, max = 128, message = "3~128字")
    private String address;
    
    private Long userId;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
}
