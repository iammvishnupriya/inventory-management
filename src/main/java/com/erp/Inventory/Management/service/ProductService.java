package com.erp.Inventory.Management.service;

import com.erp.Inventory.Management.dto.ProductDto;
import com.erp.Inventory.Management.dto.SuccessResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<SuccessResponse<ProductDto>> createProduct(ProductDto dto);

    ResponseEntity<SuccessResponse<ProductDto>> updateProduct(String id, ProductDto dto);

    ResponseEntity<SuccessResponse<String>> deleteProduct(String id);

    ResponseEntity<SuccessResponse<ProductDto>> getProductById(String id);

    ResponseEntity<SuccessResponse<List<ProductDto>>> getAllProducts();
    public SuccessResponse<List<ProductDto>> getProductsWithLowStock();

}
