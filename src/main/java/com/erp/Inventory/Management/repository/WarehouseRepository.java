package com.erp.Inventory.Management.repository;

import com.erp.Inventory.Management.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Optional<Warehouse> findByLocation(String location);
    List<Warehouse> findByCapacityGreaterThanEqual(Integer capacity);
}