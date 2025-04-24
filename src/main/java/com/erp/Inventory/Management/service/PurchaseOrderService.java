package com.erp.Inventory.Management.service;

import com.erp.Inventory.Management.dto.PurchaseOrderDto;

import java.time.LocalDate;
import java.util.List;

/**
 * Service interface for purchase order operations
 */
public interface PurchaseOrderService {
    /**
     * Create a new purchase order and update stock
     * @param dto The purchase order data
     * @return The created purchase order
     */
    PurchaseOrderDto createPurchaseOrder(PurchaseOrderDto dto);
    
    /**
     * Get a purchase order by ID
     * @param id The purchase order ID
     * @return The purchase order
     */
    PurchaseOrderDto getPurchaseOrderById(Long id);
    
    /**
     * Get all purchase orders
     * @return List of all purchase orders
     */
    List<PurchaseOrderDto> getAllPurchaseOrders();
    
    /**
     * Get purchase orders by supplier ID
     * @param supplierId The supplier ID
     * @return List of purchase orders for the supplier
     */
    List<PurchaseOrderDto> getPurchaseOrdersBySupplier(Long supplierId);
    
    /**
     * Get purchase orders by product ID
     * @param productId The product ID
     * @return List of purchase orders for the product
     */
    List<PurchaseOrderDto> getPurchaseOrdersByProduct(Long productId);
    
    /**
     * Get purchase orders within a date range
     * @param startDate The start date
     * @param endDate The end date
     * @return List of purchase orders within the date range
     */
    List<PurchaseOrderDto> getPurchaseOrdersByDateRange(LocalDate startDate, LocalDate endDate);
    
    /**
     * Process a purchase order receipt (mark as received and update stock)
     * @param id The purchase order ID
     * @param receivedQuantity The quantity received (may be different from ordered)
     * @return The updated purchase order
     */
    PurchaseOrderDto receivePurchaseOrder(Long id, Integer receivedQuantity);
    
    /**
     * Cancel a purchase order
     * @param id The purchase order ID
     */
    void cancelPurchaseOrder(Long id);
}