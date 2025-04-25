package com.erp.Inventory.Management.dto;

public class StockDto {
    private Integer id;
    private Integer productId;
    private String productName;
    private String productSku;
    private Integer warehouseId;
    private String warehouseLocation;
    private Integer quantity;

    public StockDto() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getProductSku() { return productSku; }
    public void setProductSku(String productSku) { this.productSku = productSku; }

    public Integer getWarehouseId() { return warehouseId; }
    public void setWarehouseId(Integer warehouseId) { this.warehouseId = warehouseId; }

    public String getWarehouseLocation() { return warehouseLocation; }
    public void setWarehouseLocation(String warehouseLocation) { this.warehouseLocation = warehouseLocation; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
