package com.erp.Inventory.Management.dto;

import com.erp.Inventory.Management.model.PurchaseOrderEntity;
import java.time.LocalDate;

public class PurchaseOrderDto {
    private Long id;
    private Long supplierId;
    private String supplierName;
    private Long productId;
    private String productName;
    private String productSku;
    private Integer quantity;
    private Integer receivedQuantity;
    private LocalDate orderDate;
    private LocalDate receivedDate;
    private PurchaseOrderEntity.Status status;
    private Double unitPrice;
    private Double totalPrice;
    private String notes;

    public PurchaseOrderDto() {
    }

    public PurchaseOrderDto(Long id, Long supplierId, String supplierName, Long productId, String productName,
                           String productSku, Integer quantity, Integer receivedQuantity, LocalDate orderDate,
                           LocalDate receivedDate, PurchaseOrderEntity.Status status, Double unitPrice,
                           Double totalPrice, String notes) {
        this.id = id;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.productId = productId;
        this.productName = productName;
        this.productSku = productSku;
        this.quantity = quantity;
        this.receivedQuantity = receivedQuantity;
        this.orderDate = orderDate;
        this.receivedDate = receivedDate;
        this.status = status;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(Integer receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public PurchaseOrderEntity.Status getStatus() {
        return status;
    }

    public void setStatus(PurchaseOrderEntity.Status status) {
        this.status = status;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Builder pattern implementation
    public static PurchaseOrderDtoBuilder builder() {
        return new PurchaseOrderDtoBuilder();
    }

    public static class PurchaseOrderDtoBuilder {
        private Long id;
        private Long supplierId;
        private String supplierName;
        private Long productId;
        private String productName;
        private String productSku;
        private Integer quantity;
        private Integer receivedQuantity;
        private LocalDate orderDate;
        private LocalDate receivedDate;
        private PurchaseOrderEntity.Status status;
        private Double unitPrice;
        private Double totalPrice;
        private String notes;

        PurchaseOrderDtoBuilder() {
        }

        public PurchaseOrderDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PurchaseOrderDtoBuilder supplierId(Long supplierId) {
            this.supplierId = supplierId;
            return this;
        }

        public PurchaseOrderDtoBuilder supplierName(String supplierName) {
            this.supplierName = supplierName;
            return this;
        }

        public PurchaseOrderDtoBuilder productId(Long productId) {
            this.productId = productId;
            return this;
        }

        public PurchaseOrderDtoBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public PurchaseOrderDtoBuilder productSku(String productSku) {
            this.productSku = productSku;
            return this;
        }

        public PurchaseOrderDtoBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public PurchaseOrderDtoBuilder receivedQuantity(Integer receivedQuantity) {
            this.receivedQuantity = receivedQuantity;
            return this;
        }

        public PurchaseOrderDtoBuilder orderDate(LocalDate orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public PurchaseOrderDtoBuilder receivedDate(LocalDate receivedDate) {
            this.receivedDate = receivedDate;
            return this;
        }

        public PurchaseOrderDtoBuilder status(PurchaseOrderEntity.Status status) {
            this.status = status;
            return this;
        }

        public PurchaseOrderDtoBuilder unitPrice(Double unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public PurchaseOrderDtoBuilder totalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public PurchaseOrderDtoBuilder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public PurchaseOrderDto build() {
            return new PurchaseOrderDto(id, supplierId, supplierName, productId, productName, productSku, quantity,
                    receivedQuantity, orderDate, receivedDate, status, unitPrice, totalPrice, notes);
        }
    }
}