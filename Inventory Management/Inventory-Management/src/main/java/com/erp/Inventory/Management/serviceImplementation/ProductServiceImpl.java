package com.erp.Inventory.Management.serviceImplementation;

import com.erp.Inventory.Management.dto.ProductDto;
import com.erp.Inventory.Management.dto.SuccessResponse;
import com.erp.Inventory.Management.model.Product;
import com.erp.Inventory.Management.repository.ProductRepository;
import com.erp.Inventory.Management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<SuccessResponse<ProductDto>> createProduct(ProductDto dto) {
        Product product = mapToEntity(dto);
        Product savedProduct = productRepository.save(product);
        ProductDto savedDto = mapToDto(savedProduct);
        return ResponseEntity.ok(new SuccessResponse<>(200, "Product added successfully", savedDto));
    }

    @Override
    public ResponseEntity<SuccessResponse<ProductDto>> updateProduct(String id, ProductDto dto) {
        Optional<Product> productOpt = productRepository.findById(Long.valueOf(id));
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setName(dto.getName());
            product.setSku(dto.getSku());
            product.setPrice(dto.getPrice());
            product.setCategory(dto.getCategory());
            product.setStockQuantity(dto.getStockQuantity());

            Product updatedProduct = productRepository.save(product);
            ProductDto updatedDto = mapToDto(updatedProduct);

            return ResponseEntity.ok(new SuccessResponse<>(200, "Product updated successfully", updatedDto));
        } else {
            return ResponseEntity.status(404).body(new SuccessResponse<>(404, "Product not found", null));
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> deleteProduct(String id) {
        Optional<Product> productOpt = productRepository.findById(Long.valueOf(id));
        if (productOpt.isPresent()) {
            productRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.ok(new SuccessResponse<>(200, "Product deleted successfully", id));
        } else {
            return ResponseEntity.status(404).body(new SuccessResponse<>(404, "Product not found", null));
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<ProductDto>> getProductById(String id) {
        Optional<Product> productOpt = productRepository.findById(Long.valueOf(id));
        if (productOpt.isPresent()) {
            ProductDto dto = mapToDto(productOpt.get());
            return ResponseEntity.ok(new SuccessResponse<>(200, "Product found", dto));
        } else {
            return ResponseEntity.status(404).body(new SuccessResponse<>(404, "Product not found", null));
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<List<ProductDto>>> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> dtoList = productList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new SuccessResponse<>(200, "Product list fetched", dtoList));
    }

    private ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setSku(product.getSku());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory());
        dto.setStockQuantity(product.getStockQuantity());
        return dto;
    }

    private Product mapToEntity(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setSku(dto.getSku());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        product.setStockQuantity(dto.getStockQuantity());
        return product;
    }
}
