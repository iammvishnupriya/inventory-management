package com.erp.Inventory.Management.repository;

import com.erp.Inventory.Management.model.Product;
import com.erp.Inventory.Management.model.PurchaseOrderEntity;
import com.erp.Inventory.Management.model.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderEntity, Long> {
    List<PurchaseOrderEntity> findBySupplier(SupplierEntity supplier);
    List<PurchaseOrderEntity> findByProduct(Product product);
    List<PurchaseOrderEntity> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);
}