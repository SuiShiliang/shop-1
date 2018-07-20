package shop.model;

import javax.validation.constraints.NotEmpty;

public class Cellphone {
    private Long id;
    @NotEmpty(message = "必填")
    private String brand;
    private String model;
    private String os;
    private String cpuBrand;
    private Integer cpuCoreCount;
    private Integer ram;
    private Integer storage;
    private String color;
    private String description;
    private Integer price;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getOs() {
        return os;
    }
    public void setOs(String os) {
        this.os = os;
    }
    public String getCpuBrand() {
        return cpuBrand;
    }
    public void setCpuBrand(String cpuBrand) {
        this.cpuBrand = cpuBrand;
    }
    public Integer getCpuCoreCount() {
        return cpuCoreCount;
    }
    public void setCpuCoreCount(Integer cpuCoreCount) {
        this.cpuCoreCount = cpuCoreCount;
    }
    public Integer getRam() {
        return ram;
    }
    public void setRam(Integer ram) {
        this.ram = ram;
    }
    public Integer getStorage() {
        return storage;
    }
    public void setStorage(Integer storage) {
        this.storage = storage;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
}
