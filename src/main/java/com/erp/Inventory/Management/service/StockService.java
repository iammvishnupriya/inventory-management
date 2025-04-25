package com.erp.Inventory.Management.service;

import com.erp.Inventory.Management.dto.StockDto;
import com.erp.Inventory.Management.dto.SuccessResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StockService {
    ResponseEntity<SuccessResponse<StockDto>> createStock(StockDto stockDto);
    ResponseEntity<SuccessResponse<StockDto>> updateStock(Integer id, StockDto stockDto);
    ResponseEntity<SuccessResponse<String>> deleteStock(Integer id);
    ResponseEntity<SuccessResponse<StockDto>> getStockById(Integer id);
    ResponseEntity<SuccessResponse<List<StockDto>>> getAllStocks();
}
