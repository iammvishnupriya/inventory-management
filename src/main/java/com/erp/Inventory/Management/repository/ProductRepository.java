package com.erp.Inventory.Management.repository;

import com.erp.Inventory.Management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsBySku(String sku);
    @Query("SELECT p FROM Product p WHERE p.sku = :sku AND p.status = 'ACTIVE'")
    Optional<Product> findBySku(@Param("sku") String sku);

    List<Product> findByCategoryId(Integer categoryId);

}