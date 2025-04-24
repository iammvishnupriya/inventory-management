package com.erp.Inventory.Management.service;

import com.erp.Inventory.Management.dto.WarehouseDto;

import java.util.List;

public interface WareHouseService {
    WarehouseDto createWarehouse(WarehouseDto dto);
    WarehouseDto updateWarehouse(Long id, WarehouseDto dto);
    WarehouseDto getWarehouseById(Long id);
    List<WarehouseDto> getAllWarehouses();
    void deleteWarehouse(Long id);
}
