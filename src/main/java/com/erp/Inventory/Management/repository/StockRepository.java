package com.erp.Inventory.Management.repository;

import com.erp.Inventory.Management.model.Product;
import com.erp.Inventory.Management.model.Stock;
import com.erp.Inventory.Management.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Stock entity
 */
@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    /**
     * Find stocks by product
     * @param product The product
     * @return List of stocks for the product
     */
    List<Stock> findByProduct(Product product);
    
    /**
     * Find stocks by warehouse
     * @param warehouse The warehouse
     * @return List of stocks in the warehouse
     */
    List<Stock> findByWarehouse(Warehouse warehouse);
    
    /**
     * Find stock by product and warehouse
     * @param product The product
     * @param warehouse The warehouse
     * @return Optional stock
     */
    Optional<Stock> findByProductAndWarehouse(Product product, Warehouse warehouse);
    
    /**
     * Get total quantity of a product across all warehouses
     * @param product The product
     * @return Total quantity
     */
    @Query("SELECT SUM(s.quantity) FROM Stock s WHERE s.product = ?1")
    Integer getTotalQuantityByProduct(Product product);
    
    /**
     * Find stock by product ID
     * @param productId The product ID
     * @return Optional stock
     */
    Optional<Stock> findByProductId(Long productId);
    
    /**
     * Find stocks with quantity less than or equal to threshold
     * @param threshold The threshold quantity
     * @return List of stocks below threshold
     */
    List<Stock> findByQuantityLessThanEqual(Integer threshold);
    
    /**
     * Find stocks with quantity equal to zero
     * @return List of out-of-stock items
     */
    List<Stock> findByQuantity(Integer quantity);
}