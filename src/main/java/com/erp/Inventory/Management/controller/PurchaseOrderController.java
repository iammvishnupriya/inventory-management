//package com.erp.Inventory.Management.controller;
//import com.erp.Inventory.Management.dto.PurchaseOrderDto;
//import com.erp.Inventory.Management.service.PurchaseOrderService;
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
// * REST controller for purchase order operations
// */
//@RestController
//@RequestMapping("/api/purchase-orders")
//@RequiredArgsConstructor
//public class PurchaseOrderController {
//
//    private final PurchaseOrderService purchaseOrderService;
//
//    /**
//     * Create a new purchase order
//     * @param dto The purchase order data
//     * @return ResponseEntity with created purchase order
//     */
//    @PostMapping
//    public ResponseEntity<PurchaseOrderDto> createPurchaseOrder(@RequestBody PurchaseOrderDto dto) {
//        return new ResponseEntity<>(purchaseOrderService.createPurchaseOrder(dto), HttpStatus.CREATED);
//    }
//
//    /**
//     * Get a purchase order by ID
//     * @param id The purchase order ID
//     * @return ResponseEntity with purchase order
//     */
//    @GetMapping("/{id}")
//    public ResponseEntity<PurchaseOrderDto> getPurchaseOrderById(@PathVariable Long id) {
//        return ResponseEntity.ok(purchaseOrderService.getPurchaseOrderById(id));
//    }
//
//    /**
//     * Get all purchase orders
//     * @return ResponseEntity with list of purchase orders
//     */
//    @GetMapping
//    public ResponseEntity<List<PurchaseOrderDto>> getAllPurchaseOrders() {
//        return ResponseEntity.ok(purchaseOrderService.getAllPurchaseOrders());
//    }
//
//    /**
//     * Get purchase orders by supplier ID
//     * @param supplierId The supplier ID
//     * @return ResponseEntity with list of purchase orders
//     */
//    @GetMapping("/supplier/{supplierId}")
//    public ResponseEntity<List<PurchaseOrderDto>> getPurchaseOrdersBySupplier(@PathVariable Long supplierId) {
//        return ResponseEntity.ok(purchaseOrderService.getPurchaseOrdersBySupplier(supplierId));
//    }
//
//    /**
//     * Get purchase orders by product ID
//     * @param productId The product ID
//     * @return ResponseEntity with list of purchase orders
//     */
//    @GetMapping("/product/{productId}")
//    public ResponseEntity<List<PurchaseOrderDto>> getPurchaseOrdersByProduct(@PathVariable Long productId) {
//        return ResponseEntity.ok(purchaseOrderService.getPurchaseOrdersByProduct(productId));
//    }
//
//    /**
//     * Get purchase orders by date range
//     * @param startDate The start date
//     * @param endDate The end date
//     * @return ResponseEntity with list of purchase orders
//     */
//    @GetMapping("/date-range")
//    public ResponseEntity<List<PurchaseOrderDto>> getPurchaseOrdersByDateRange(
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
//        return ResponseEntity.ok(purchaseOrderService.getPurchaseOrdersByDateRange(startDate, endDate));
//    }
//
//    /**
//     * Process a purchase order receipt
//     * @param id The purchase order ID
//     * @param request Map containing received quantity
//     * @return ResponseEntity with updated purchase order
//     */
//    @PostMapping("/{id}/receive")
//    public ResponseEntity<PurchaseOrderDto> receivePurchaseOrder(
//            @PathVariable Long id,
//            @RequestBody Map<String, Integer> request) {
//
//        Integer receivedQuantity = request.get("receivedQuantity");
//        if (receivedQuantity == null) {
//            throw new IllegalArgumentException("Received quantity is required");
//        }
//
//        return ResponseEntity.ok(purchaseOrderService.receivePurchaseOrder(id, receivedQuantity));
//    }
//
//    /**
//     * Cancel a purchase order
//     * @param id The purchase order ID
//     * @return ResponseEntity with no content
//     */
//    @PostMapping("/{id}/cancel")
//    public ResponseEntity<Void> cancelPurchaseOrder(@PathVariable Long id) {
//        purchaseOrderService.cancelPurchaseOrder(id);
//        return ResponseEntity.noContent().build();
//    }
//}