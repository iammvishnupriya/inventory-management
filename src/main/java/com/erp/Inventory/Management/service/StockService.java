package com.erp.Inventory.Management.service;

import com.erp.Inventory.Management.dto.StockDto;

import java.util.List;

/**
 * Service interface for stock management operations
 */
public interface StockService {
    /**
     * Updates stock level for a product
     * @param productId The ID of the product
     * @param quantity The new quantity
     * @return The updated StockDto
     */
    StockDto updateStock(Long productId, Integer quantity);
    
    /**
     * Gets current stock level for a product
     * @param productId The ID of the product
     * @return The StockDto with current level
     */
    StockDto getStockByProductId(Long productId);
    
    /**
     * Gets all stock entries
     * @return List of all StockDtos
     */
    List<StockDto> getAllStocks();
    
    /**
     * Gets all stock entries with quantity below threshold
     * @param threshold The threshold quantity
     * @return List of StockDtos below threshold
     */
    List<StockDto> getLowStockItems(Integer threshold);
    
    /**
     * Adjusts stock level (add or subtract)
     * @param productId The ID of the product
     * @param adjustment The adjustment amount (positive or negative)
     * @param reason The reason for adjustment
     * @return The updated StockDto
     */
    StockDto adjustStock(Long productId, Integer adjustment, String reason);
}