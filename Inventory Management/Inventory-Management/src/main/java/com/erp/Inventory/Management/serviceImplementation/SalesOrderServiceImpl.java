//package com.erp.Inventory.Management.serviceImplementation;
//
//import com.erp.Inventory.Management.dto.SalesOrderDto;
//import com.erp.Inventory.Management.dto.StockDto;
//import com.erp.Inventory.Management.exception.ProductException;
//import com.erp.Inventory.Management.model.Product;
//import com.erp.Inventory.Management.model.SalesOrder;
//import com.erp.Inventory.Management.repository.ProductRepository;
//import com.erp.Inventory.Management.repository.SalesOrderRepository;
//import com.erp.Inventory.Management.service.SalesOrderService;
//import com.erp.Inventory.Management.service.StockService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * Implementation of the SalesOrderService interface
// */
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class SalesOrderServiceImpl implements SalesOrderService {
//
//    private final SalesOrderRepository salesOrderRepository;
//    private final ProductRepository productRepository;
//    private final StockService stockService;
//
//    /**
//     * Convert SalesOrder entity to SalesOrderDto
//     * @param entity The entity to convert
//     * @return The converted DTO
//     */
//    private SalesOrderDto toDto(SalesOrder entity) {
//        if (entity == null) {
//            return null;
//        }
//
//        SalesOrderDto dto = new SalesOrderDto();
//        BeanUtils.copyProperties(entity, dto);
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
//     * Convert SalesOrderDto to SalesOrder entity
//     * @param dto The DTO to convert
//     * @return The converted entity
//     */
//    private SalesOrder toEntity(SalesOrderDto dto) {
//        if (dto == null) {
//            return null;
//        }
//
//        SalesOrder entity = new SalesOrder();
//
//        // Copy simple properties
//        entity.setId(dto.getId());
//        entity.setCustomerName(dto.getCustomerName());
//        entity.setCustomerEmail(dto.getCustomerEmail());
//        entity.setCustomerPhone(dto.getCustomerPhone());
//        entity.setQuantity(dto.getQuantity());
//        entity.setFulfilledQuantity(dto.getFulfilledQuantity());
//        entity.setOrderDate(dto.getOrderDate());
//        entity.setFulfillmentDate(dto.getFulfillmentDate());
//        entity.setStatus(dto.getStatus());
//        entity.setUnitPrice(dto.getUnitPrice());
//        entity.setTotalPrice(dto.getTotalPrice());
//        entity.setNotes(dto.getNotes());
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
//     * Check if there is enough stock for a product
//     * @param productId The product ID
//     * @param requiredQuantity The required quantity
//     * @throws IllegalStateException if there is not enough stock
//     */
//    private void checkStockAvailability(Long productId, Integer requiredQuantity) {
//        try {
//            StockDto stockDto = stockService.getStockByProductId(productId);
//
//            if (stockDto.getQuantity() < requiredQuantity) {
//                throw new IllegalStateException(
//                        "Not enough stock for product ID: " + productId +
//                        ". Available: " + stockDto.getQuantity() +
//                        ", Required: " + requiredQuantity
//                );
//            }
//        } catch (Exception e) {
//            if (e instanceof IllegalStateException) {
//                throw e;
//            }
//            log.error("Error checking stock availability: {}", e.getMessage(), e);
//            throw new RuntimeException("Failed to check stock availability: " + e.getMessage(), e);
//        }
//    }
//
//    /**
//     * Create a new sales order
//     * @param dto The sales order data
//     * @return The created sales order
//     */
//    @Override
//    @Transactional
//    public SalesOrderDto createSalesOrder(SalesOrderDto dto) {
//        log.info("Creating new sales order for product ID: {}, quantity: {}", dto.getProductId(), dto.getQuantity());
//
//        // Validate input
//        if (dto.getProductId() == null) {
//            throw new IllegalArgumentException("Product ID is required");
//        }
//
//        if (dto.getQuantity() == null || dto.getQuantity() <= 0) {
//            throw new IllegalArgumentException("Quantity must be greater than zero");
//        }
//
//        if (dto.getCustomerName() == null || dto.getCustomerName().trim().isEmpty()) {
//            throw new IllegalArgumentException("Customer name is required");
//        }
//
//        // Check stock availability
//        checkStockAvailability(dto.getProductId(), dto.getQuantity());
//
//        // Set default values
//        if (dto.getOrderDate() == null) {
//            dto.setOrderDate(LocalDate.now());
//        }
//
//        dto.setStatus(SalesOrder.Status.PENDING);
//
//        // Get product price if not provided
//        if (dto.getUnitPrice() == null) {
//            Product product = productRepository.findById(dto.getProductId())
//                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + dto.getProductId()));
//            dto.setUnitPrice(product.getPrice());
//        }
//
//        // Calculate total price
//        dto.setTotalPrice(dto.getUnitPrice() * dto.getQuantity());
//
//        // Convert to entity and save
//        SalesOrder entity = toEntity(dto);
//        SalesOrder saved = salesOrderRepository.save(entity);
//
//        log.info("Successfully created sales order with ID: {}", saved.getId());
//        return toDto(saved);
//    }
//
//    /**
//     * Get a sales order by ID
//     * @param id The sales order ID
//     * @return The sales order
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public SalesOrderDto getSalesOrderById(Long id) {
//        log.info("Fetching sales order with ID: {}", id);
//
//        SalesOrder entity = salesOrderRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Sales order not found with ID: " + id));
//
//        return toDto(entity);
//    }
//
//    /**
//     * Get all sales orders
//     * @return List of all sales orders
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public List<SalesOrderDto> getAllSalesOrders() {
//        log.info("Fetching all sales orders");
//
//        List<SalesOrder> entities = salesOrderRepository.findAll();
//        log.info("Found {} sales orders", entities.size());
//
//        return entities.stream()
//                .map(this::toDto)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Get sales orders by product ID
//     * @param productId The product ID
//     * @return List of sales orders for the product
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public List<SalesOrderDto> getSalesOrdersByProduct(Long productId) {
//        log.info("Fetching sales orders for product ID: {}", productId);
//
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
//
//        List<SalesOrder> entities = salesOrderRepository.findByProduct(product);
//        log.info("Found {} sales orders for product ID: {}", entities.size(), productId);
//
//        return entities.stream()
//                .map(this::toDto)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Get sales orders by customer name
//     * @param customerName The customer name
//     * @return List of sales orders for the customer
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public List<SalesOrderDto> getSalesOrdersByCustomer(String customerName) {
//        log.info("Fetching sales orders for customer: {}", customerName);
//
//        List<SalesOrder> entities = salesOrderRepository.findByCustomerNameContainingIgnoreCase(customerName);
//        log.info("Found {} sales orders for customer: {}", entities.size(), customerName);
//
//        return entities.stream()
//                .map(this::toDto)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Get sales orders within a date range
//     * @param startDate The start date
//     * @param endDate The end date
//     * @return List of sales orders within the date range
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public List<SalesOrderDto> getSalesOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
//        log.info("Fetching sales orders between {} and {}", startDate, endDate);
//
//        List<SalesOrder> entities = salesOrderRepository.findByOrderDateBetween(startDate, endDate);
//        log.info("Found {} sales orders in date range", entities.size());
//
//        return entities.stream()
//                .map(this::toDto)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Get sales orders by status
//     * @param status The status
//     * @return List of sales orders with the status
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public List<SalesOrderDto> getSalesOrdersByStatus(SalesOrder.Status status) {
//        log.info("Fetching sales orders with status: {}", status);
//
//        List<SalesOrder> entities = salesOrderRepository.findByStatus(status);
//        log.info("Found {} sales orders with status: {}", entities.size(), status);
//
//        return entities.stream()
//                .map(this::toDto)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Fulfill a sales order (mark as fulfilled and update stock)
//     * @param id The sales order ID
//     * @param fulfilledQuantity The quantity fulfilled (may be different from ordered)
//     * @return The updated sales order
//     */
//    @Override
//    @Transactional
//    public SalesOrderDto fulfillSalesOrder(Long id, Integer fulfilledQuantity) {
//        log.info("Fulfilling sales order ID: {}, fulfilled quantity: {}", id, fulfilledQuantity);
//
//        if (fulfilledQuantity == null || fulfilledQuantity <= 0) {
//            throw new IllegalArgumentException("Fulfilled quantity must be greater than zero");
//        }
//
//        // Get the sales order
//        SalesOrder order = salesOrderRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Sales order not found with ID: " + id));
//
//        // Check if order can be fulfilled
//        if (order.getStatus() == SalesOrder.Status.FULFILLED) {
//            throw new IllegalStateException("Sales order is already fulfilled");
//        }
//
//        if (order.getStatus() == SalesOrder.Status.CANCELLED) {
//            throw new IllegalStateException("Cannot fulfill a cancelled sales order");
//        }
//
//        if (order.getStatus() == SalesOrder.Status.RETURNED) {
//            throw new IllegalStateException("Cannot fulfill a returned sales order");
//        }
//
//        // Check if fulfilled quantity is valid
//        if (fulfilledQuantity > order.getQuantity()) {
//            throw new IllegalArgumentException("Fulfilled quantity cannot exceed ordered quantity");
//        }
//
//        // Check stock availability
//        checkStockAvailability(order.getProduct().getId(), fulfilledQuantity);
//
//        // Update order status and fulfilled quantity
//        order.setFulfilledQuantity(fulfilledQuantity);
//        order.setFulfillmentDate(LocalDate.now());
//
//        if (fulfilledQuantity.equals(order.getQuantity())) {
//            order.setStatus(SalesOrder.Status.FULFILLED);
//        } else {
//            order.setStatus(SalesOrder.Status.PARTIAL);
//        }
//
//        // Update stock
//        try {
//            // Use the stock service to adjust the stock level (negative adjustment for sales)
//            stockService.adjustStock(
//                    order.getProduct().getId(),
//                    -fulfilledQuantity,
//                    "Sales order fulfillment: SO#" + order.getId()
//            );
//
//            log.info("Successfully updated stock for product ID: {}", order.getProduct().getId());
//        } catch (Exception e) {
//            log.error("Error updating stock: {}", e.getMessage(), e);
//            throw new RuntimeException("Failed to update stock: " + e.getMessage(), e);
//        }
//
//        // Save the updated order
//        SalesOrder saved = salesOrderRepository.save(order);
//        log.info("Successfully fulfilled sales order ID: {}", saved.getId());
//
//        return toDto(saved);
//    }
//
//    /**
//     * Cancel a sales order
//     * @param id The sales order ID
//     */
//    @Override
//    @Transactional
//    public void cancelSalesOrder(Long id) {
//        log.info("Cancelling sales order with ID: {}", id);
//
//        // Get the sales order
//        SalesOrder order = salesOrderRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Sales order not found with ID: " + id));
//
//        // Check if order can be cancelled
//        if (order.getStatus() == SalesOrder.Status.FULFILLED || order.getStatus() == SalesOrder.Status.PARTIAL) {
//            throw new IllegalStateException("Cannot cancel a fulfilled or partially fulfilled sales order");
//        }
//
//        if (order.getStatus() == SalesOrder.Status.CANCELLED) {
//            log.info("Sales order is already cancelled");
//            return;
//        }
//
//        if (order.getStatus() == SalesOrder.Status.RETURNED) {
//            throw new IllegalStateException("Cannot cancel a returned sales order");
//        }
//
//        // Update status
//        order.setStatus(SalesOrder.Status.CANCELLED);
//
//        // Save the updated order
//        salesOrderRepository.save(order);
//        log.info("Successfully cancelled sales order with ID: {}", id);
//    }
//
//    /**
//     * Process a sales order return
//     * @param id The sales order ID
//     * @param returnedQuantity The quantity returned
//     * @param reason The reason for return
//     * @return The updated sales order
//     */
//    @Override
//    @Transactional
//    public SalesOrderDto processSalesReturn(Long id, Integer returnedQuantity, String reason) {
//        log.info("Processing return for sales order ID: {}, returned quantity: {}, reason: {}", id, returnedQuantity, reason);
//
//        if (returnedQuantity == null || returnedQuantity <= 0) {
//            throw new IllegalArgumentException("Returned quantity must be greater than zero");
//        }
//
//        if (reason == null || reason.trim().isEmpty()) {
//            throw new IllegalArgumentException("Return reason is required");
//        }
//
//        // Get the sales order
//        SalesOrder order = salesOrderRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Sales order not found with ID: " + id));
//
//        // Check if order can be returned
//        if (order.getStatus() == SalesOrder.Status.PENDING) {
//            throw new IllegalStateException("Cannot return a pending sales order");
//        }
//
//        if (order.getStatus() == SalesOrder.Status.CANCELLED) {
//            throw new IllegalStateException("Cannot return a cancelled sales order");
//        }
//
//        if (order.getStatus() == SalesOrder.Status.RETURNED) {
//            throw new IllegalStateException("Sales order is already returned");
//        }
//
//        // Check if returned quantity is valid
//        if (returnedQuantity > order.getFulfilledQuantity()) {
//            throw new IllegalArgumentException("Returned quantity cannot exceed fulfilled quantity");
//        }
//
//        // Update order status
//        order.setStatus(SalesOrder.Status.RETURNED);
//        order.setNotes(reason);
//
//        // Update stock
//        try {
//            // Use the stock service to adjust the stock level (positive adjustment for returns)
//            stockService.adjustStock(
//                    order.getProduct().getId(),
//                    returnedQuantity,
//                    "Sales order return: SO#" + order.getId() + " - " + reason
//            );
//
//            log.info("Successfully updated stock for product ID: {}", order.getProduct().getId());
//        } catch (Exception e) {
//            log.error("Error updating stock: {}", e.getMessage(), e);
//            throw new RuntimeException("Failed to update stock: " + e.getMessage(), e);
//        }
//
//        // Save the updated order
//        SalesOrder saved = salesOrderRepository.save(order);
//        log.info("Successfully processed return for sales order ID: {}", saved.getId());
//
//        return toDto(saved);
//    }
//}