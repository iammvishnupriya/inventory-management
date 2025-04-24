//package com.erp.Inventory.Management.controller;
//
//import com.erp.Inventory.Management.dto.StockDto;
//import com.erp.Inventory.Management.service.StockService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * REST controller for stock management operations
// */
//@RestController
//@RequestMapping("/api/stocks")
//@RequiredArgsConstructor
//public class StockController {
//
//    private final StockService stockService;
//
//    /**
//     * Get stock by product ID
//     * @param productId The ID of the product
//     * @return ResponseEntity with StockDto
//     */
//    @GetMapping("/product/{productId}")
//    public ResponseEntity<StockDto> getStockByProductId(@PathVariable Long productId) {
//        return ResponseEntity.ok(stockService.getStockByProductId(productId));
//    }
//
//    /**
//     * Get all stocks
//     * @return ResponseEntity with list of StockDtos
//     */
//    @GetMapping
//    public ResponseEntity<List<StockDto>> getAllStocks() {
//        return ResponseEntity.ok(stockService.getAllStocks());
//    }
//
//    /**
//     * Get low stock items
//     * @param threshold The threshold quantity
//     * @return ResponseEntity with list of StockDtos below threshold
//     */
//    @GetMapping("/low-stock")
//    public ResponseEntity<List<StockDto>> getLowStockItems(@RequestParam(defaultValue = "10") Integer threshold) {
//        return ResponseEntity.ok(stockService.getLowStockItems(threshold));
//    }
//
//    /**
//     * Update stock quantity
//     * @param productId The ID of the product
//     * @param request Map containing the new quantity
//     * @return ResponseEntity with updated StockDto
//     */
//    @PutMapping("/product/{productId}")
//    public ResponseEntity<StockDto> updateStock(
//            @PathVariable Long productId,
//            @RequestBody Map<String, Integer> request) {
//
//        Integer quantity = request.get("quantity");
//        if (quantity == null) {
//            throw new IllegalArgumentException("Quantity is required");
//        }
//
//        return ResponseEntity.ok(stockService.updateStock(productId, quantity));
//    }
//
//    /**
//     * Adjust stock quantity
//     * @param productId The ID of the product
//     * @param request Map containing adjustment amount and reason
//     * @return ResponseEntity with updated StockDto
//     */
//    @PatchMapping("/product/{productId}/adjust")
//    public ResponseEntity<StockDto> adjustStock(
//            @PathVariable Long productId,
//            @RequestBody Map<String, Object> request) {
//
//        Integer adjustment = (Integer) request.get("adjustment");
//        String reason = (String) request.get("reason");
//
//        if (adjustment == null) {
//            throw new IllegalArgumentException("Adjustment amount is required");
//        }
//
//        if (reason == null || reason.trim().isEmpty()) {
//            throw new IllegalArgumentException("Adjustment reason is required");
//        }
//
//        return ResponseEntity.ok(stockService.adjustStock(productId, adjustment, reason));
//    }
//}