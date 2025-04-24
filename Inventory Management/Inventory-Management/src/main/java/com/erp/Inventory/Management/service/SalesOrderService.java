package com.erp.Inventory.Management.service;

import com.erp.Inventory.Management.dto.SalesOrderDto;
import com.erp.Inventory.Management.model.SalesOrder;

import java.time.LocalDate;
import java.util.List;

/**
 * Service interface for sales order operations
 */
public interface SalesOrderService {
    /**
     * Create a new sales order
     * @param dto The sales order data
     * @return The created sales order
     */
    SalesOrderDto createSalesOrder(SalesOrderDto dto);
    
    /**
     * Get a sales order by ID
     * @param id The sales order ID
     * @return The sales order
     */
    SalesOrderDto getSalesOrderById(Long id);
    
    /**
     * Get all sales orders
     * @return List of all sales orders
     */
    List<SalesOrderDto> getAllSalesOrders();
    
    /**
     * Get sales orders by product ID
     * @param productId The product ID
     * @return List of sales orders for the product
     */
    List<SalesOrderDto> getSalesOrdersByProduct(Long productId);
    
    /**
     * Get sales orders by customer name
     * @param customerName The customer name
     * @return List of sales orders for the customer
     */
    List<SalesOrderDto> getSalesOrdersByCustomer(String customerName);
    
    /**
     * Get sales orders within a date range
     * @param startDate The start date
     * @param endDate The end date
     * @return List of sales orders within the date range
     */
    List<SalesOrderDto> getSalesOrdersByDateRange(LocalDate startDate, LocalDate endDate);
    
    /**
     * Get sales orders by status
     * @param status The status
     * @return List of sales orders with the status
     */
    List<SalesOrderDto> getSalesOrdersByStatus(SalesOrder.Status status);
    
    /**
     * Fulfill a sales order (mark as fulfilled and update stock)
     * @param id The sales order ID
     * @param fulfilledQuantity The quantity fulfilled (may be different from ordered)
     * @return The updated sales order
     */
    SalesOrderDto fulfillSalesOrder(Long id, Integer fulfilledQuantity);
    
    /**
     * Cancel a sales order
     * @param id The sales order ID
     */
    void cancelSalesOrder(Long id);
    
    /**
     * Process a sales order return
     * @param id The sales order ID
     * @param returnedQuantity The quantity returned
     * @param reason The reason for return
     * @return The updated sales order
     */
    SalesOrderDto processSalesReturn(Long id, Integer returnedQuantity, String reason);
}