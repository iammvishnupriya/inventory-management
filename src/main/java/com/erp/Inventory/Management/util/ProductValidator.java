package com.erp.Inventory.Management.util;

import com.erp.Inventory.Management.dto.ProductDto;

/**
 * Utility class for product validation
 */
public class ProductValidator {

    /**
     * Validates product data
     * @param dto The ProductDto to validate
     * @throws IllegalArgumentException if validation fails
     */
    public static void validateProduct(ProductDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Product data cannot be null");
        }
        
        validateName(dto.getName());
        validateSku(dto.getSku());
        validatePrice(dto.getPrice());
    }
    
    /**
     * Validates product name
     * @param name The product name to validate
     * @throws IllegalArgumentException if validation fails
     */
    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        
        if (name.length() > 100) {
            throw new IllegalArgumentException("Product name cannot exceed 100 characters");
        }
    }
    
    /**
     * Validates product SKU
     * @param sku The product SKU to validate
     * @throws IllegalArgumentException if validation fails
     */
    public static void validateSku(String sku) {
        if (sku == null || sku.trim().isEmpty()) {
            throw new IllegalArgumentException("Product SKU cannot be empty");
        }
        
        if (sku.length() > 50) {
            throw new IllegalArgumentException("Product SKU cannot exceed 50 characters");
        }
        
        // SKU should be alphanumeric
        if (!sku.matches("^[a-zA-Z0-9-_]+$")) {
            throw new IllegalArgumentException("Product SKU must contain only letters, numbers, hyphens, and underscores");
        }
    }
    
    /**
     * Validates product price
     * @param price The product price to validate
     * @throws IllegalArgumentException if validation fails
     */
    public static void validatePrice(Double price) {
        if (price == null) {
            throw new IllegalArgumentException("Product price cannot be null");
        }
        
        if (price <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }
        
        if (price > 1000000) {
            throw new IllegalArgumentException("Product price cannot exceed 1,000,000");
        }
    }
}