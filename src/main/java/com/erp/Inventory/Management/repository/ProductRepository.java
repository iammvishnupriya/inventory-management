package com.erp.Inventory.Management.repository;

import com.erp.Inventory.Management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsBySku(String sku);
    Optional<Product> findBySku(String sku);
    List<Product> findByCategory_Id(Long categoryId);
}