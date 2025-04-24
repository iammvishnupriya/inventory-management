//package com.erp.Inventory.Management.serviceImplementation;
//
//import com.erp.Inventory.Management.dto.StockDto;
//import com.erp.Inventory.Management.exception.ProductException;
//import com.erp.Inventory.Management.model.Product;
//import com.erp.Inventory.Management.model.Stock;
//import com.erp.Inventory.Management.repository.ProductRepository;
//import com.erp.Inventory.Management.repository.StockRepository;
//import com.erp.Inventory.Management.service.StockService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * Implementation of the StockService interface
// */
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class StockServiceImpl implements StockService {
//
//    private final StockRepository stockRepository;
//    private final ProductRepository productRepository;
//
//    /**
//     * Converts a Stock entity to a StockDto
//     * @param stock The stock entity to convert
//     * @return The converted StockDto
//     */
//    private StockDto toDto(Stock stock) {
//        if (stock == null) {
//            return null;
//        }
//        StockDto dto = new StockDto();
//        BeanUtils.copyProperties(stock, dto);
//
//        // Set product information
//        if (stock.getProduct() != null) {
//            dto.setProductId(stock.getProduct().getId());
//            dto.setProductName(stock.getProduct().getName());
//            dto.setProductSku(stock.getProduct().getSku());
//        }
//
//        return dto;
//    }
//
//    /**
//     * Updates stock level for a product
//     * @param productId The ID of the product
//     * @param quantity The new quantity
//     * @return The updated StockDto
//     */
//    @Override
//    @Transactional
//    public StockDto updateStock(Long productId, Integer quantity) {
//        log.info("Updating stock for product ID: {} to quantity: {}", productId, quantity);
//
//        if (productId == null) {
//            throw new IllegalArgumentException("Product ID cannot be null");
//        }
//
//        if (quantity == null || quantity < 0) {
//            throw new IllegalArgumentException("Quantity cannot be negative");
//        }
//
//        // Find the product
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ProductException("Product not found with ID: " + productId, "PRODUCT_NOT_FOUND"));
//
//        // Find existing stock or create new one
//        Stock stock = stockRepository.findByProductId(productId)
//                .orElse(new Stock());
//
//        stock.setProduct(product);
//        stock.setQuantity(quantity);
//        stock.setLastUpdated(LocalDateTime.now());
//
//        Stock saved = stockRepository.save(stock);
//        log.info("Successfully updated stock for product ID: {}", productId);
//
//        return toDto(saved);
//    }
//
//    /**
//     * Gets current stock level for a product
//     * @param productId The ID of the product
//     * @return The StockDto with current level
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public StockDto getStockByProductId(Long productId) {
//        log.info("Fetching stock for product ID: {}", productId);
//
//        if (productId == null) {
//            throw new IllegalArgumentException("Product ID cannot be null");
//        }
//
//        // Check if product exists
//        if (!productRepository.existsById(productId)) {
//            throw new ProductException("Product not found with ID: " + productId, "PRODUCT_NOT_FOUND");
//        }
//
//        // Find stock
//        Stock stock = stockRepository.findByProductId(productId)
//                .orElseThrow(() -> new ProductException("Stock not found for product ID: " + productId, "STOCK_NOT_FOUND"));
//
//        return toDto(stock);
//    }
//
//    /**
//     * Gets all stock entries
//     * @return List of all StockDtos
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public List<StockDto> getAllStocks() {
//        log.info("Fetching all stocks");
//
//        List<Stock> stocks = stockRepository.findAll();
//        log.info("Found {} stock entries", stocks.size());
//
//        return stocks.stream()
//                .map(this::toDto)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Gets all stock entries with quantity below threshold
//     * @param threshold The threshold quantity
//     * @return List of StockDtos below threshold
//     */
//    @Override
//    @Transactional(readOnly = true)
//    public List<StockDto> getLowStockItems(Integer threshold) {
//        log.info("Fetching low stock items below threshold: {}", threshold);
//
//        if (threshold == null || threshold < 0) {
//            throw new IllegalArgumentException("Threshold cannot be negative");
//        }
//
//        List<Stock> lowStocks = stockRepository.findByQuantityLessThanEqual(threshold);
//        log.info("Found {} low stock items", lowStocks.size());
//
//        return lowStocks.stream()
//                .map(this::toDto)
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Adjusts stock level (add or subtract)
//     * @param productId The ID of the product
//     * @param adjustment The adjustment amount (positive or negative)
//     * @param reason The reason for adjustment
//     * @return The updated StockDto
//     */
//    @Override
//    @Transactional
//    public StockDto adjustStock(Long productId, Integer adjustment, String reason) {
//        log.info("Adjusting stock for product ID: {} by: {} for reason: {}", productId, adjustment, reason);
//
//        if (productId == null) {
//            throw new IllegalArgumentException("Product ID cannot be null");
//        }
//
//        if (adjustment == null) {
//            throw new IllegalArgumentException("Adjustment amount cannot be null");
//        }
//
//        if (reason == null || reason.trim().isEmpty()) {
//            throw new IllegalArgumentException("Adjustment reason cannot be empty");
//        }
//
//        // Find the product
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ProductException("Product not found with ID: " + productId, "PRODUCT_NOT_FOUND"));
//
//        // Find existing stock or create new one
//        Stock stock = stockRepository.findByProductId(productId)
//                .orElse(new Stock());
//
//        // If new stock, set product and initial quantity to 0
//        if (stock.getId() == null) {
//            stock.setProduct(product);
//            stock.setQuantity(0);
//        }
//
//        // Calculate new quantity
//        int newQuantity = stock.getQuantity() + adjustment;
//
//        // Prevent negative stock
//        if (newQuantity < 0) {
//            throw new IllegalArgumentException("Cannot adjust stock below zero. Current stock: " + stock.getQuantity());
//        }
//
//        stock.setQuantity(newQuantity);
//        stock.setLastUpdated(LocalDateTime.now());
//        stock.setLastUpdateReason(reason);
//
//        Stock saved = stockRepository.save(stock);
//        log.info("Successfully adjusted stock for product ID: {} to new quantity: {}", productId, newQuantity);
//
//        return toDto(saved);
//    }
//}