package shop.model;


public class Cellphone {
    private Long id;
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
        if (brand != null && !brand.trim().isEmpty()) {
            this.brand = brand;
        }
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
        if (os != null && !os.trim().isEmpty()) {
            this.os = os;
        }
    }
    public String getCpuBrand() {
        return cpuBrand;
    }
    public void setCpuBrand(String cpuBrand) {
        // 配合mapper.xml中做null检查
        if (cpuBrand != null && !cpuBrand.trim().isEmpty()) {
            this.cpuBrand = cpuBrand;
        }
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
    
    @Override
    public String toString() {
        return "Cellphone [id=" + id + ", brand=" + brand + ", model=" + model
                + ", os=" + os + ", cpuBrand=" + cpuBrand + ", cpuCoreCount="
                + cpuCoreCount + ", ram=" + ram + ", storage=" + storage
                + ", color=" + color + ", description=" + description
                + ", price=" + price + "]";
    }
    
}
