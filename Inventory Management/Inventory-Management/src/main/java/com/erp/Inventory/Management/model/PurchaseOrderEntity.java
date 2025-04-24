package com.erp.Inventory.Management.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrderEntity {
    
    /**
     * Enum for purchase order status
     */
    public enum Status {
        PENDING,    // Order created but not received
        RECEIVED,   // Order received completely
        PARTIAL,    // Order partially received
        CANCELLED   // Order cancelled
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    private Integer quantity;
    
    @Column(name = "received_quantity")
    private Integer receivedQuantity;
    
    private LocalDate orderDate;
    
    @Column(name = "received_date")
    private LocalDate receivedDate;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Column(name = "unit_price")
    private Double unitPrice;
    
    @Column(name = "total_price")
    private Double totalPrice;
    
    private String notes;
}