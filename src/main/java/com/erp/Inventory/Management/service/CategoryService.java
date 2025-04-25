package com.erp.Inventory.Management.service;

import com.erp.Inventory.Management.dto.CategoryDto;
import com.erp.Inventory.Management.dto.SuccessResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<SuccessResponse<CategoryDto>> create(CategoryDto dto);
    ResponseEntity<SuccessResponse<CategoryDto>> update(Integer id, CategoryDto dto);
    ResponseEntity<SuccessResponse<String>> delete(Integer id);
    ResponseEntity<SuccessResponse<CategoryDto>> get(Integer id);
    ResponseEntity<SuccessResponse<List<CategoryDto>>> getAll();
}
