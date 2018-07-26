package shop.controller.form;

import javax.validation.constraints.NotNull;

public class OrderForm {
    @NotNull(message = "请选择收货地址")
    private Long shippingAddressId;

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }
    
}
