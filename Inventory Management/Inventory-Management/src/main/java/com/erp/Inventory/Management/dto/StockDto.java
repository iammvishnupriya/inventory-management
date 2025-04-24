package com.erp.Inventory.Management.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Stock entity
 */
public class StockDto {
    private Long id;
    private Long productId;
    private String productName;
    private String productSku;
    private Long warehouseId;
    private String warehouseLocation;
    private Integer quantity;
    private LocalDateTime lastUpdated;
    private String lastUpdateReason;
    private Integer reorderLevel;
    private Integer optimalLevel;
    
    // Calculated fields
    private boolean lowStock;
    private boolean outOfStock;
    
    public StockDto() {
    }
    
    public StockDto(Long id, Long productId, String productName, String productSku, Long warehouseId,
                   String warehouseLocation, Integer quantity, LocalDateTime lastUpdated,
                   String lastUpdateReason, Integer reorderLevel, Integer optimalLevel,
                   boolean lowStock, boolean outOfStock) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productSku = productSku;
        this.warehouseId = warehouseId;
        this.warehouseLocation = warehouseLocation;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
        this.lastUpdateReason = lastUpdateReason;
        this.reorderLevel = reorderLevel;
        this.optimalLevel = optimalLevel;
        this.lowStock = lowStock;
        this.outOfStock = outOfStock;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getProductId() {
        return productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getProductSku() {
        return productSku;
    }
    
    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }
    
    public Long getWarehouseId() {
        return warehouseId;
    }
    
    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }
    
    public String getWarehouseLocation() {
        return warehouseLocation;
    }
    
    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
    
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public String getLastUpdateReason() {
        return lastUpdateReason;
    }
    
    public void setLastUpdateReason(String lastUpdateReason) {
        this.lastUpdateReason = lastUpdateReason;
    }
    
    public Integer getReorderLevel() {
        return reorderLevel;
    }
    
    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
    
    public Integer getOptimalLevel() {
        return optimalLevel;
    }
    
    public void setOptimalLevel(Integer optimalLevel) {
        this.optimalLevel = optimalLevel;
    }
    
    public boolean isLowStock() {
        if (reorderLevel == null || quantity == null) {
            return false;
        }
        return quantity <= reorderLevel;
    }
    
    public void setLowStock(boolean lowStock) {
        this.lowStock = lowStock;
    }
    
    public boolean isOutOfStock() {
        return quantity != null && quantity == 0;
    }
    
    public void setOutOfStock(boolean outOfStock) {
        this.outOfStock = outOfStock;
    }
    
    // Builder pattern implementation
    public static StockDtoBuilder builder() {
        return new StockDtoBuilder();
    }
    
    public static class StockDtoBuilder {
        private Long id;
        private Long productId;
        private String productName;
        private String productSku;
        private Long warehouseId;
        private String warehouseLocation;
        private Integer quantity;
        private LocalDateTime lastUpdated;
        private String lastUpdateReason;
        private Integer reorderLevel;
        private Integer optimalLevel;
        private boolean lowStock;
        private boolean outOfStock;
        
        StockDtoBuilder() {
        }
        
        public StockDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }
        
        public StockDtoBuilder productId(Long productId) {
            this.productId = productId;
            return this;
        }
        
        public StockDtoBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }
        
        public StockDtoBuilder productSku(String productSku) {
            this.productSku = productSku;
            return this;
        }
        
        public StockDtoBuilder warehouseId(Long warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }
        
        public StockDtoBuilder warehouseLocation(String warehouseLocation) {
            this.warehouseLocation = warehouseLocation;
            return this;
        }
        
        public StockDtoBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }
        
        public StockDtoBuilder lastUpdated(LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }
        
        public StockDtoBuilder lastUpdateReason(String lastUpdateReason) {
            this.lastUpdateReason = lastUpdateReason;
            return this;
        }
        
        public StockDtoBuilder reorderLevel(Integer reorderLevel) {
            this.reorderLevel = reorderLevel;
            return this;
        }
        
        public StockDtoBuilder optimalLevel(Integer optimalLevel) {
            this.optimalLevel = optimalLevel;
            return this;
        }
        
        public StockDtoBuilder lowStock(boolean lowStock) {
            this.lowStock = lowStock;
            return this;
        }
        
        public StockDtoBuilder outOfStock(boolean outOfStock) {
            this.outOfStock = outOfStock;
            return this;
        }
        
        public StockDto build() {
            return new StockDto(id, productId, productName, productSku, warehouseId, warehouseLocation,
                    quantity, lastUpdated, lastUpdateReason, reorderLevel, optimalLevel, lowStock, outOfStock);
        }
    }
}