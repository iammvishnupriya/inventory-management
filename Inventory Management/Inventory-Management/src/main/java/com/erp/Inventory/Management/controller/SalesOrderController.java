//package com.erp.Inventory.Management.controller;
//
//import com.erp.Inventory.Management.dto.SalesOrderDto;
//import com.erp.Inventory.Management.model.SalesOrder;
//import com.erp.Inventory.Management.service.SalesOrderService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//
///**
// * REST controller for sales order operations
// */
//@RestController
//@RequestMapping("/api/sales-orders")
//@RequiredArgsConstructor
//public class SalesOrderController {
//
//    private final SalesOrderService salesOrderService;
//
//    /**
//     * Create a new sales order
//     * @param dto The sales order data
//     * @return ResponseEntity with created sales order
//     */
//    @PostMapping
//    public ResponseEntity<SalesOrderDto> createSalesOrder(@RequestBody SalesOrderDto dto) {
//        return new ResponseEntity<>(salesOrderService.createSalesOrder(dto), HttpStatus.CREATED);
//    }
//
//    /**
//     * Get a sales order by ID
//     * @param id The sales order ID
//     * @return ResponseEntity with sales order
//     */
//    @GetMapping("/{id}")
//    public ResponseEntity<SalesOrderDto> getSalesOrderById(@PathVariable Long id) {
//        return ResponseEntity.ok(salesOrderService.getSalesOrderById(id));
//    }
//
//    /**
//     * Get all sales orders
//     * @return ResponseEntity with list of sales orders
//     */
//    @GetMapping
//    public ResponseEntity<List<SalesOrderDto>> getAllSalesOrders() {
//        return ResponseEntity.ok(salesOrderService.getAllSalesOrders());
//    }
//
//    /**
//     * Get sales orders by product ID
//     * @param productId The product ID
//     * @return ResponseEntity with list of sales orders
//     */
//    @GetMapping("/product/{productId}")
//    public ResponseEntity<List<SalesOrderDto>> getSalesOrdersByProduct(@PathVariable Long productId) {
//        return ResponseEntity.ok(salesOrderService.getSalesOrdersByProduct(productId));
//    }
//
//    /**
//     * Get sales orders by customer name
//     * @param customerName The customer name
//     * @return ResponseEntity with list of sales orders
//     */
//    @GetMapping("/customer")
//    public ResponseEntity<List<SalesOrderDto>> getSalesOrdersByCustomer(@RequestParam String customerName) {
//        return ResponseEntity.ok(salesOrderService.getSalesOrdersByCustomer(customerName));
//    }
//
//    /**
//     * Get sales orders by date range
//     * @param startDate The start date
//     * @param endDate The end date
//     * @return ResponseEntity with list of sales orders
//     */
//    @GetMapping("/date-range")
//    public ResponseEntity<List<SalesOrderDto>> getSalesOrdersByDateRange(
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
//        return ResponseEntity.ok(salesOrderService.getSalesOrdersByDateRange(startDate, endDate));
//    }
//
//    /**
//     * Get sales orders by status
//     * @param status The status
//     * @return ResponseEntity with list of sales orders
//     */
//    @GetMapping("/status/{status}")
//    public ResponseEntity<List<SalesOrderDto>> getSalesOrdersByStatus(@PathVariable SalesOrder.Status status) {
//        return ResponseEntity.ok(salesOrderService.getSalesOrdersByStatus(status));
//    }
//
//    /**
//     * Fulfill a sales order
//     * @param id The sales order ID
//     * @param request Map containing fulfilled quantity
//     * @return ResponseEntity with updated sales order
//     */
//    @PostMapping("/{id}/fulfill")
//    public ResponseEntity<SalesOrderDto> fulfillSalesOrder(
//            @PathVariable Long id,
//            @RequestBody Map<String, Integer> request) {
//
//        Integer fulfilledQuantity = request.get("fulfilledQuantity");
//        if (fulfilledQuantity == null) {
//            throw new IllegalArgumentException("Fulfilled quantity is required");
//        }
//
//        return ResponseEntity.ok(salesOrderService.fulfillSalesOrder(id, fulfilledQuantity));
//    }
//
//    /**
//     * Cancel a sales order
//     * @param id The sales order ID
//     * @return ResponseEntity with no content
//     */
//    @PostMapping("/{id}/cancel")
//    public ResponseEntity<Void> cancelSalesOrder(@PathVariable Long id) {
//        salesOrderService.cancelSalesOrder(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    /**
//     * Process a sales order return
//     * @param id The sales order ID
//     * @param request Map containing returned quantity and reason
//     * @return ResponseEntity with updated sales order
//     */
//    @PostMapping("/{id}/return")
//    public ResponseEntity<SalesOrderDto> processSalesReturn(
//            @PathVariable Long id,
//            @RequestBody Map<String, Object> request) {
//
//        Integer returnedQuantity = (Integer) request.get("returnedQuantity");
//        String reason = (String) request.get("reason");
//
//        if (returnedQuantity == null) {
//            throw new IllegalArgumentException("Returned quantity is required");
//        }
//
//        if (reason == null || reason.trim().isEmpty()) {
//            throw new IllegalArgumentException("Return reason is required");
//        }
//
//        return ResponseEntity.ok(salesOrderService.processSalesReturn(id, returnedQuantity, reason));
//    }
//}