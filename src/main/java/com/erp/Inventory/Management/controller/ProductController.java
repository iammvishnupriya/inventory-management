package com.erp.Inventory.Management.controller;

import com.erp.Inventory.Management.dto.ProductDto;
import com.erp.Inventory.Management.dto.SuccessResponse;
import com.erp.Inventory.Management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/inventory_management/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<SuccessResponse<ProductDto>> createProduct(@RequestBody ProductDto dto) {
        return productService.createProduct(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SuccessResponse<ProductDto>> updateProduct(@PathVariable Integer id, @RequestBody ProductDto dto) {
        return productService.updateProduct(id, dto);
    }

    @DeleteMapping("/deleteByProductCode")
    public ResponseEntity<SuccessResponse<String>> deleteProduct(@RequestParam String productCode) {
        return productService.deleteProduct(productCode);
    }

    @GetMapping("/getByProductCode")
    public ResponseEntity<SuccessResponse<ProductDto>> getProductById(@RequestParam String productCode) {
        return productService.getProductById(productCode);
    }

    @GetMapping("/getAllProductByCategoryId")
    public ResponseEntity<SuccessResponse<List<ProductDto>>> getAllProducts(@RequestParam(required=false) Integer categoryId) {
        return productService.getAllProducts(categoryId);
    }
}
