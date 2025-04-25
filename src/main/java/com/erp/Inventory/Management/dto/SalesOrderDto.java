package com.erp.Inventory.Management.dto;

import com.erp.Inventory.Management.model.SalesOrder;
import java.time.LocalDate;

public class SalesOrderDto {
    private Integer id;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private Long productId;
    private String productName;
    private String productSku;
    private Integer quantity;
    private Integer fulfilledQuantity;
    private LocalDate orderDate;
    private LocalDate fulfillmentDate;
    private SalesOrder.Status status;
    private Double unitPrice;
    private Double totalPrice;
    private String notes;

    public SalesOrderDto() {
    }

    public SalesOrderDto(Integer id, String customerName, String customerEmail, String customerPhone, Long productId,
                        String productName, String productSku, Integer quantity, Integer fulfilledQuantity,
                        LocalDate orderDate, LocalDate fulfillmentDate, SalesOrder.Status status,
                        Double unitPrice, Double totalPrice, String notes) {
        this.id = id;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.productId = productId;
        this.productName = productName;
        this.productSku = productSku;
        this.quantity = quantity;
        this.fulfilledQuantity = fulfilledQuantity;
        this.orderDate = orderDate;
        this.fulfillmentDate = fulfillmentDate;
        this.status = status;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.notes = notes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
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

    public Integer getFulfilledQuantity() {
        return fulfilledQuantity;
    }

    public void setFulfilledQuantity(Integer fulfilledQuantity) {
        this.fulfilledQuantity = fulfilledQuantity;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getFulfillmentDate() {
        return fulfillmentDate;
    }

    public void setFulfillmentDate(LocalDate fulfillmentDate) {
        this.fulfillmentDate = fulfillmentDate;
    }

    public SalesOrder.Status getStatus() {
        return status;
    }

    public void setStatus(SalesOrder.Status status) {
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
    public static SalesOrderDtoBuilder builder() {
        return new SalesOrderDtoBuilder();
    }

    public static class SalesOrderDtoBuilder {
        private Integer id;
        private String customerName;
        private String customerEmail;
        private String customerPhone;
        private Long productId;
        private String productName;
        private String productSku;
        private Integer quantity;
        private Integer fulfilledQuantity;
        private LocalDate orderDate;
        private LocalDate fulfillmentDate;
        private SalesOrder.Status status;
        private Double unitPrice;
        private Double totalPrice;
        private String notes;

        SalesOrderDtoBuilder() {
        }

        public SalesOrderDtoBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public SalesOrderDtoBuilder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public SalesOrderDtoBuilder customerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
            return this;
        }

        public SalesOrderDtoBuilder customerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
            return this;
        }

        public SalesOrderDtoBuilder productId(Long productId) {
            this.productId = productId;
            return this;
        }

        public SalesOrderDtoBuilder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public SalesOrderDtoBuilder productSku(String productSku) {
            this.productSku = productSku;
            return this;
        }

        public SalesOrderDtoBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public SalesOrderDtoBuilder fulfilledQuantity(Integer fulfilledQuantity) {
            this.fulfilledQuantity = fulfilledQuantity;
            return this;
        }

        public SalesOrderDtoBuilder orderDate(LocalDate orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public SalesOrderDtoBuilder fulfillmentDate(LocalDate fulfillmentDate) {
            this.fulfillmentDate = fulfillmentDate;
            return this;
        }

        public SalesOrderDtoBuilder status(SalesOrder.Status status) {
            this.status = status;
            return this;
        }

        public SalesOrderDtoBuilder unitPrice(Double unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public SalesOrderDtoBuilder totalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public SalesOrderDtoBuilder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public SalesOrderDto build() {
            return new SalesOrderDto(id, customerName, customerEmail, customerPhone, productId, productName, productSku,
                    quantity, fulfilledQuantity, orderDate, fulfillmentDate, status, unitPrice, totalPrice, notes);
        }
    }
}