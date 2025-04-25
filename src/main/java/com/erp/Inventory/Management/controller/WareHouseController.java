package com.erp.Inventory.Management.controller;

import com.erp.Inventory.Management.dto.SuccessResponse;
import com.erp.Inventory.Management.dto.WarehouseDto;
import com.erp.Inventory.Management.service.WareHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory_management/api/warehouses")
@RequiredArgsConstructor
public class WareHouseController {

    private WareHouseService wareHouseService;

    public WareHouseController(WareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }

    @PostMapping("/add")
    public ResponseEntity<SuccessResponse<WarehouseDto>> create(@RequestBody WarehouseDto dto) {
        return ResponseEntity.ok(new SuccessResponse<>(200, "Warehouse created successfully", wareHouseService.createWarehouse(dto)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SuccessResponse<WarehouseDto>> update(@PathVariable Long id, @RequestBody WarehouseDto dto) {
        return ResponseEntity.ok(new SuccessResponse<>(200, "Warehouse updated successfully", wareHouseService.updateWarehouse(id, dto)));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SuccessResponse<WarehouseDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new SuccessResponse<>(200, "Warehouse fetched successfully", wareHouseService.getWarehouseById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<SuccessResponse<List<WarehouseDto>>> getAll() {
        return ResponseEntity.ok(new SuccessResponse<>(200, "All warehouses fetched", wareHouseService.getAllWarehouses()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessResponse<String>> delete(@PathVariable Long id) {
        wareHouseService.deleteWarehouse(id);
        return ResponseEntity.ok(new SuccessResponse<>(200, "Warehouse deleted successfully", "Deleted"));
    }
}
