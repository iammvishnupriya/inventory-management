package com.erp.Inventory.Management.repository;

import com.erp.Inventory.Management.model.Product;
import com.erp.Inventory.Management.model.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for SalesOrder entity
 */
@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {
    /**
     * Find sales orders by product
     * @param product The product
     * @return List of sales orders for the product
     */
    List<SalesOrder> findByProduct(Product product);
    
    /**
     * Find sales orders by customer name
     * @param customerName The customer name
     * @return List of sales orders for the customer
     */
    List<SalesOrder> findByCustomerNameContainingIgnoreCase(String customerName);
    
    /**
     * Find sales orders by order date between
     * @param startDate The start date
     * @param endDate The end date
     * @return List of sales orders within the date range
     */
    List<SalesOrder> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);
    
    /**
     * Find sales orders by status
     * @param status The status
     * @return List of sales orders with the status
     */
    List<SalesOrder> findByStatus(SalesOrder.Status status);
}