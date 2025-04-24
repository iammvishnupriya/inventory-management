//package com.erp.Inventory.Management.serviceImplementation;
//
//import com.erp.Inventory.Management.dto.PurchaseOrderDto;
//import com.erp.Inventory.Management.exception.ProductException;
//import com.erp.Inventory.Management.model.Product;
//import com.erp.Inventory.Management.model.PurchaseOrderEntity;
//import com.erp.Inventory.Management.model.SupplierEntity;
//import com.erp.Inventory.Management.repository.ProductRepository;
//import com.erp.Inventory.Management.repository.PurchaseOrderRepository;
//import com.erp.Inventory.Management.service.PurchaseOrderService;
//import com.erp.Inventory.Management.service.StockService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * Implementation of the PurchaseOrderService interface
// */
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class PurchaseOrderServiceImpl implements PurchaseOrderService {
//
//    private final PurchaseOrderRepository purchaseOrderRepository;
//    private final ProductRepository productRepository;
////    private final SupplierRepository supplierRepository;
//    private final StockService stockService;
//
//    /**
//     * Convert PurchaseOrderEntity to PurchaseOrderDto
//     * @param entity The entity to convert
//     * @return The converted DTO
//     */
//    private PurchaseOrderDto toDto(PurchaseOrderEntity entity) {
//        if (entity == null) {
//            return null;
//        }
//        PurchaseOrderDto dto = new PurchaseOrderDto();
//        BeanUtils.copyProperties(entity, dto);
//
//        // Set supplier information
//        if (entity.getSupplier() != null) {
//            dto.setSupplierId(entity.getSupplier().getId());
//            dto.setSupplierName(entity.getSupplier().getName());
//        }
//
//        // Set product information
//        if (entity.getProduct() != null) {
//            dto.setProductId(entity.getProduct().getId());
//            dto.setProductName(entity.getProduct().getName());
//            dto.setProductSku(entity.getProduct().getSku());
//        }
//
//        return dto;
//    }
//
//    /**
//     * Convert PurchaseOrderDto to PurchaseOrderEntity
//     * @param dto The DTO to convert
//     * @return The converted entity
//     */
//    private PurchaseOrderEntity toEntity(PurchaseOrderDto dto) {
//        if (dto == null) {
//            return null;
//        }
//
//        PurchaseOrderEntity entity = new PurchaseOrderEntity();
//
//        // Copy simple properties
//        entity.setId(dto.getId());
//        entity.setQuantity(dto.getQuantity());
//        entity.setReceivedQuantity(dto.getReceivedQuantity());
//        entity.setOrderDate(dto.getOrderDate());
//        entity.setReceivedDate(dto.getReceivedDate());
//        entity.setStatus(dto.getStatus());
//        entity.setUnitPrice(dto.getUnitPrice());
//        entity.setTotalPrice(dto.getTotalPrice());
//        entity.setNotes(dto.getNotes());
//        // Set supplier
//        if (dto.getSupplierId() != null) {
//            SupplierEntity supplier = supplierRepository.findById(dto.getSupplierId())
//                    .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + dto.getSupplierId()));
//            entity.setSupplier(supplier);
//        }
//
//        // Set product
//        if (dto.getProductId() != null) {
//            Product product = productRepository.findById(dto.getProductId())
//                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + dto.getProductId()));
//            entity.setProduct(product);
//        }
//
//        return entity;
//    }
//
//    /**
//     * Create a new purchase order
//     * @param dto The purchase order data
//     * @return The created purchase order
//     */
//    @Override
//    @Transactional
//    public PurchaseOrderDto createPurchaseOrder(PurchaseOrderDto dto) {
//        log.info("Creating new purchase order for product ID: {}, quantity: {}", dto.getProductId(), dto.getQuantity());
//
//        // Validate input
//        if (dto.getProductId() == null) {
//            throw new IllegalArgumentException("Product ID is required");
//        }
//
//        if (dto.getSupplierId() == null) {
//            throw new IllegalArgumentException("Supplier ID is required");
//        }
//
//        if (dto.getQuantity() == null || dto.getQuantity() <= 0) {
//            throw new IllegalArgumentException("Quantity must be greater than zero");
//        }
//
//        // Set default values
//        if (dto.getOrderDate() == null) {
//            dto.setOrderDate(LocalDate.now());
//        }
//
//        dto.setStatus(PurchaseOrderEntity.Status.PENDING);
//
//        // Calculate total price if unit price is provided
//        if (dto.getUnitPrice() != null) {
//            dto.setTotalPrice(dto.getUnitPrice() * dto.getQuantity());
//        }
//
//        // Convert to entity and save
//        PurchaseOrderEntity entity = toEntity(dto);
//        PurchaseOrderEntity saved = purchaseOrderRepository.save(entity);
//
//        log.info("Successfully created purchase order with ID: {}", saved.getId());
//        return toDto(saved);
//    }
//
//    /**
//     * Get a purchase order by ID
//     * @param id The purchase order ID
//     * @return The purchase order
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public PurchaseOrderDto getPurchaseOrderById(Long id) {
//        log.info("Fetching purchase order with ID: {}", id);
//
//        PurchaseOrderEntity entity = purchaseOrderRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Purchase order not found with ID: " + id));
//
//        return toDto(entity);
//    }
//
//    /**
//     * Get all purchase orders
//     * @return List of all purchase orders
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public List<PurchaseOrderDto> getAllPurchaseOrders() {
//        log.info("Fetching all purchase orders");
//
//        List<PurchaseOrderEntity> entities = purchaseOrderRepository.findAll();
//        log.info("Found {} purchase orders", entities.size());
//
//        return entities.stream()
//                .map(this::toDto)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Get purchase orders by supplier ID
//     * @param supplierId The supplier ID
//     * @return List of purchase orders for the supplier
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public List<PurchaseOrderDto> getPurchaseOrdersBySupplier(Long supplierId) {
//        log.info("Fetching purchase orders for supplier ID: {}", supplierId);
//
//        SupplierEntity supplier = supplierRepository.findById(supplierId)
//                .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + supplierId));
//
//        List<PurchaseOrderEntity> entities = purchaseOrderRepository.findBySupplier(supplier);
//        log.info("Found {} purchase orders for supplier ID: {}", entities.size(), supplierId);
//
//        return entities.stream()
//                .map(this::toDto)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Get purchase orders by product ID
//     * @param productId The product ID
//     * @return List of purchase orders for the product
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public List<PurchaseOrderDto> getPurchaseOrdersByProduct(Long productId) {
//        log.info("Fetching purchase orders for product ID: {}", productId);
//
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
//
//        List<PurchaseOrderEntity> entities = purchaseOrderRepository.findByProduct(product);
//        log.info("Found {} purchase orders for product ID: {}", entities.size(), productId);
//
//        return entities.stream()
//                .map(this::toDto)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Get purchase orders within a date range
//     * @param startDate The start date
//     * @param endDate The end date
//     * @return List of purchase orders within the date range
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public List<PurchaseOrderDto> getPurchaseOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
//        log.info("Fetching purchase orders between {} and {}", startDate, endDate);
//
//        List<PurchaseOrderEntity> entities = purchaseOrderRepository.findByOrderDateBetween(startDate, endDate);
//        log.info("Found {} purchase orders in date range", entities.size());
//
//        return entities.stream()
//                .map(this::toDto)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Process a purchase order receipt (mark as received and update stock)
//     * @param id The purchase order ID
//     * @param receivedQuantity The quantity received (may be different from ordered)
//     * @return The updated purchase order
//     */
//    @Override
//    @Transactional
//    public PurchaseOrderDto receivePurchaseOrder(Long id, Integer receivedQuantity) {
//        log.info("Processing receipt for purchase order ID: {}, received quantity: {}", id, receivedQuantity);
//
//        if (receivedQuantity == null || receivedQuantity < 0) {
//            throw new IllegalArgumentException("Received quantity cannot be negative");
//        }
//
//        // Get the purchase order
//        PurchaseOrderEntity order = purchaseOrderRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Purchase order not found with ID: " + id));
//
//        // Check if order is already received or cancelled
//        if (order.getStatus() == PurchaseOrderEntity.Status.RECEIVED) {
//            throw new IllegalStateException("Purchase order is already received");
//        }
//
//        if (order.getStatus() == PurchaseOrderEntity.Status.CANCELLED) {
//            throw new IllegalStateException("Cannot receive a cancelled purchase order");
//        }
//
//        // Update order status and received quantity
//        order.setReceivedQuantity(receivedQuantity);
//        order.setReceivedDate(LocalDate.now());
//
//        if (receivedQuantity.equals(order.getQuantity())) {
//            order.setStatus(PurchaseOrderEntity.Status.RECEIVED);
//        } else if (receivedQuantity > 0) {
//            order.setStatus(PurchaseOrderEntity.Status.PARTIAL);
//        }
//
//        // Update stock
//        try {
//            // Use the stock service to adjust the stock level
//            stockService.adjustStock(
//                    order.getProduct().getId(),
//                    receivedQuantity,
//                    "Purchase order receipt: PO#" + order.getId()
//            );
//
//            log.info("Successfully updated stock for product ID: {}", order.getProduct().getId());
//        } catch (Exception e) {
//            log.error("Error updating stock: {}", e.getMessage(), e);
//            throw new RuntimeException("Failed to update stock: " + e.getMessage(), e);
//        }
//
//        // Save the updated order
//        PurchaseOrderEntity saved = purchaseOrderRepository.save(order);
//        log.info("Successfully processed receipt for purchase order ID: {}", saved.getId());
//
//        return toDto(saved);
//    }
//
//    /**
//     * Cancel a purchase order
//     * @param id The purchase order ID
//     */
//    @Override
//    @Transactional
//    public void cancelPurchaseOrder(Long id) {
//        log.info("Cancelling purchase order with ID: {}", id);
//
//        // Get the purchase order
//        PurchaseOrderEntity order = purchaseOrderRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Purchase order not found with ID: " + id));
//
//        // Check if order can be cancelled
//        if (order.getStatus() == PurchaseOrderEntity.Status.RECEIVED) {
//            throw new IllegalStateException("Cannot cancel a received purchase order");
//        }
//
//        if (order.getStatus() == PurchaseOrderEntity.Status.CANCELLED) {
//            log.info("Purchase order is already cancelled");
//            return;
//        }
//
//        // If partially received, we need to handle that case
//        if (order.getStatus() == PurchaseOrderEntity.Status.PARTIAL) {
//            log.warn("Cancelling a partially received purchase order. Received quantity: {}", order.getReceivedQuantity());
//        }
//
//        // Update status
//        order.setStatus(PurchaseOrderEntity.Status.CANCELLED);
//
//        // Save the updated order
//        purchaseOrderRepository.save(order);
//        log.info("Successfully cancelled purchase order with ID: {}", id);
//    }
//}