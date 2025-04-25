package com.erp.Inventory.Management.controller;

import com.erp.Inventory.Management.dto.StockDto;
import com.erp.Inventory.Management.dto.SuccessResponse;
import com.erp.Inventory.Management.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory_management/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/add")
    public ResponseEntity<SuccessResponse<StockDto>> createStock(@RequestBody StockDto stockDto) {
        return stockService.createStock(stockDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SuccessResponse<StockDto>> updateStock(@PathVariable Integer id, @RequestBody StockDto stockDto) {
        return stockService.updateStock(id, stockDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessResponse<String>> deleteStock(@PathVariable Integer id) {
        return stockService.deleteStock(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SuccessResponse<StockDto>> getStockById(@PathVariable Integer id) {
        return stockService.getStockById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<SuccessResponse<List<StockDto>>> getAllStocks() {
        return stockService.getAllStocks();
    }
}
