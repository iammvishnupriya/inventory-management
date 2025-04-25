package com.erp.Inventory.Management.serviceImplementation;

import com.erp.Inventory.Management.dto.WarehouseDto;
import com.erp.Inventory.Management.exception.ResourceNotFoundException;
import com.erp.Inventory.Management.model.Warehouse;
import com.erp.Inventory.Management.repository.WarehouseRepository;
import com.erp.Inventory.Management.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WareHouseServiceImpl implements WareHouseService {

    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WareHouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public WarehouseDto createWarehouse(WarehouseDto dto) {
        Warehouse warehouse = new Warehouse();
        warehouse.setLocation(dto.getLocation());
        warehouse.setCapacity(dto.getCapacity());
        Warehouse saved = warehouseRepository.save(warehouse);
        return mapToDto(saved);
    }

    @Override
    public WarehouseDto updateWarehouse(Long id, WarehouseDto dto) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with id " + id));

        warehouse.setLocation(dto.getLocation());
        warehouse.setCapacity(dto.getCapacity());
        Warehouse updated = warehouseRepository.save(warehouse);
        return mapToDto(updated);
    }

    @Override
    public WarehouseDto getWarehouseById(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with id " + id));
        return mapToDto(warehouse);
    }

    @Override
    public List<WarehouseDto> getAllWarehouses() {
        return warehouseRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteWarehouse(Long id) {
        if (!warehouseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Warehouse not found with id " + id);
        }
        warehouseRepository.deleteById(id);
    }

    private WarehouseDto mapToDto(Warehouse warehouse) {
        return new WarehouseDto(
                warehouse.getId(),
                warehouse.getLocation(),
                warehouse.getCapacity()
        );
    }
}
