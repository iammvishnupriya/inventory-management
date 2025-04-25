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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessResponse<String>> deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SuccessResponse<ProductDto>> getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<SuccessResponse<List<ProductDto>>> getAllProducts() {
        return productService.getAllProducts();
    }
}
