package com.erp.Inventory.Management.serviceImplementation;

import com.erp.Inventory.Management.dto.StockDto;
import com.erp.Inventory.Management.dto.SuccessResponse;
import com.erp.Inventory.Management.model.Product;
import com.erp.Inventory.Management.model.Stock;
import com.erp.Inventory.Management.model.Warehouse;
import com.erp.Inventory.Management.repository.ProductRepository;
import com.erp.Inventory.Management.repository.StockRepository;
import com.erp.Inventory.Management.repository.WarehouseRepository;
import com.erp.Inventory.Management.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public ResponseEntity<SuccessResponse<StockDto>> createStock(StockDto stockDto) {
        try {
            Optional<Product> productOpt = productRepository.findById(stockDto.getProductId());
            Optional<Warehouse> warehouseOpt = warehouseRepository.findById(stockDto.getWarehouseId());

            if (productOpt.isEmpty() || warehouseOpt.isEmpty()) {
                return ResponseEntity.status(404).body(new SuccessResponse<>(404, "Product or Warehouse not found", null));
            }

            Stock stock = new Stock();
            stock.setProduct(productOpt.get());
            stock.setWarehouse(warehouseOpt.get());
            stock.setQuantity(stockDto.getQuantity());

            Stock savedStock = stockRepository.save(stock);
            stockDto.setId(savedStock.getId());

            return ResponseEntity.ok(new SuccessResponse<>(200, "Stock created successfully", stockDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new SuccessResponse<>(500, "Internal Server Error: " + e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<StockDto>> updateStock(Integer id, StockDto stockDto) {
        try {
            Optional<Stock> optionalStock = stockRepository.findById(id);

            if (optionalStock.isPresent()) {
                Stock stock = optionalStock.get();
                stock.setQuantity(stockDto.getQuantity());

                Stock updated = stockRepository.save(stock);
                stockDto.setId(updated.getId());
                stockDto.setProductId(updated.getProduct().getId());
                stockDto.setWarehouseId(updated.getWarehouse().getId());

                return ResponseEntity.ok(new SuccessResponse<>(200, "Stock updated successfully", stockDto));
            } else {
                return ResponseEntity.status(404).body(new SuccessResponse<>(404, "Stock not found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new SuccessResponse<>(500, "Internal Server Error: " + e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> deleteStock(Integer id) {
        try {
            Optional<Stock> optionalStock = stockRepository.findById(id);
            if (optionalStock.isPresent()) {
                stockRepository.deleteById(id);
                return ResponseEntity.ok(new SuccessResponse<>(200, "Stock deleted successfully", null));
            } else {
                return ResponseEntity.status(404).body(new SuccessResponse<>(404, "Stock not found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new SuccessResponse<>(500, "Internal Server Error: " + e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<StockDto>> getStockById(Integer id) {
        try {
            Optional<Stock> optionalStock = stockRepository.findById(id);

            if (optionalStock.isPresent()) {
                Stock stock = optionalStock.get();
                StockDto dto = new StockDto();
                dto.setId(stock.getId());
                dto.setProductId(stock.getProduct().getId());
                dto.setProductName(stock.getProduct().getName());
                dto.setProductSku(stock.getProduct().getSku());
                dto.setWarehouseId(stock.getWarehouse().getId());
                dto.setWarehouseLocation(stock.getWarehouse().getLocation());
                dto.setQuantity(stock.getQuantity());

                return ResponseEntity.ok(new SuccessResponse<>(200, "Stock fetched successfully", dto));
            } else {
                return ResponseEntity.status(404).body(new SuccessResponse<>(404, "Stock not found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new SuccessResponse<>(500, "Internal Server Error: " + e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<List<StockDto>>> getAllStocks() {
        try {
            List<Stock> stocks = stockRepository.findAll();
            List<StockDto> stockDtos = stocks.stream().map(stock -> {
                StockDto dto = new StockDto();
                dto.setId(stock.getId());
                dto.setProductId(stock.getProduct().getId());
                dto.setProductName(stock.getProduct().getName());
                dto.setProductSku(stock.getProduct().getSku());
                dto.setWarehouseId(stock.getWarehouse().getId());
                dto.setWarehouseLocation(stock.getWarehouse().getLocation());
                dto.setQuantity(stock.getQuantity());
                return dto;
            }).collect(Collectors.toList());

            return ResponseEntity.ok(new SuccessResponse<>(200, "Stocks fetched successfully", stockDtos));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new SuccessResponse<>(500, "Internal Server Error: " + e.getMessage(), null));
        }
    }
}
