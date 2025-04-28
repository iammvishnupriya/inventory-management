package com.erp.Inventory.Management.serviceImplementation;

import com.erp.Inventory.Management.dto.ProductDto;
import com.erp.Inventory.Management.dto.SuccessResponse;
import com.erp.Inventory.Management.model.Category;
import com.erp.Inventory.Management.model.Product;
import com.erp.Inventory.Management.repository.CategoryRepository;
import com.erp.Inventory.Management.repository.ProductRepository;
import com.erp.Inventory.Management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<SuccessResponse<ProductDto>> createProduct(ProductDto dto) {
        if (dto == null) {
            return ResponseEntity.badRequest().body(new SuccessResponse<>(400, "Product data is null", null));
        }
        if (dto.getCategoryId() == null) {
            return ResponseEntity.badRequest().body(new SuccessResponse<>(400, "Category ID is required", null));
        }

        Optional<Category> categoryOpt = categoryRepository.findById(dto.getCategoryId());
        if (categoryOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(new SuccessResponse<>(400, "Category not found", null));
        }

        Product product = new Product();
        product.setName(dto.getName());
        product.setSku(dto.getSku());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setCategory(categoryOpt.get());

        Product saved = productRepository.save(product);

        ProductDto productDto = new ProductDto();
        productDto.setId(saved.getId());
        productDto.setName(saved.getName());
        productDto.setSku(saved.getSku());
        productDto.setPrice(saved.getPrice());
        productDto.setStockQuantity(saved.getStockQuantity());
        productDto.setCategoryId(saved.getCategory().getId());

        return ResponseEntity.ok(new SuccessResponse<>(200, "Product created", productDto));
    }

    @Override
    public ResponseEntity<SuccessResponse<ProductDto>> updateProduct(Integer id, ProductDto dto) {
        if (id == null || dto == null) {
            return ResponseEntity.badRequest().body(new SuccessResponse<>(400, "Product ID and data are required", null));
        }

        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(new SuccessResponse<>(400, "Product not found", null));
        }

        if (dto.getCategoryId() == null) {
            return ResponseEntity.badRequest().body(new SuccessResponse<>(400, "Category ID is required", null));
        }

        Optional<Category> categoryOpt = categoryRepository.findById(dto.getCategoryId());
        if (categoryOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(new SuccessResponse<>(400, "Category not found", null));
        }

        Product product = productOpt.get();
        product.setName(dto.getName());
        product.setSku(dto.getSku());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setCategory(categoryOpt.get());

        Product updated = productRepository.save(product);

        ProductDto productDto = new ProductDto();
        productDto.setId(updated.getId());
        productDto.setName(updated.getName());
        productDto.setSku(updated.getSku());
        productDto.setPrice(updated.getPrice());
        productDto.setStockQuantity(updated.getStockQuantity());
        productDto.setCategoryId(updated.getCategory().getId());

        return ResponseEntity.ok(new SuccessResponse<>(200, "Product updated", productDto));
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> deleteProduct(Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(new SuccessResponse<>(400, "Product ID is required", null));
        }

        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(new SuccessResponse<>(400, "Product not found", null));
        }

        productRepository.deleteById(id);
        return ResponseEntity.ok(new SuccessResponse<>(200, "Product deleted", "Deleted successfully"));
    }

    @Override
    public ResponseEntity<SuccessResponse<ProductDto>> getProductById(Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(new SuccessResponse<>(400, "Product ID is required", null));
        }

        Optional<Product> productOpt = productRepository.findById(id);
        return productOpt.map(product -> {
                    ProductDto productDto = new ProductDto();
                    productDto.setId(product.getId());
                    productDto.setName(product.getName());
                    productDto.setSku(product.getSku());
                    productDto.setPrice(product.getPrice());
                    productDto.setStockQuantity(product.getStockQuantity());
                    if (product.getCategory() != null) {
                        productDto.setCategoryId(product.getCategory().getId());
                    }
                    return ResponseEntity.ok(new SuccessResponse<>(200, "Product found", productDto));
                })
                .orElseGet(() -> ResponseEntity.badRequest().body(new SuccessResponse<>(400, "Product not found", null)));
    }

    @Override
    public ResponseEntity<SuccessResponse<List<ProductDto>>> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        if (productList == null || productList.isEmpty()) {
            return ResponseEntity.ok(new SuccessResponse<>(200, "No products available", Collections.emptyList()));
        }

        List<ProductDto> products = productList.stream()
                .map(product -> {
                    ProductDto productDto = new ProductDto();
                    productDto.setId(product.getId());
                    productDto.setName(product.getName());
                    productDto.setSku(product.getSku());
                    productDto.setPrice(product.getPrice());
                    productDto.setStockQuantity(product.getStockQuantity());
                    if (product.getCategory() != null) {
                        productDto.setCategoryName(product.getCategory().getCategoryName());
                    }
                    return productDto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(new SuccessResponse<>(200, "All products", products));
    }
}
