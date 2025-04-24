package com.erp.Inventory.Management.controller;

import com.erp.Inventory.Management.dto.CategoryDto;
import com.erp.Inventory.Management.dto.SuccessResponse;
import com.erp.Inventory.Management.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory_management/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<SuccessResponse<CategoryDto>> create(@RequestBody CategoryDto dto) {
        System.out.println("Entering the add categroy");
        return categoryService.create(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SuccessResponse<CategoryDto>> update(@PathVariable Long id, @RequestBody CategoryDto dto) {
        return categoryService.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessResponse<String>> delete(@PathVariable Long id) {
        return categoryService.delete(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SuccessResponse<CategoryDto>> get(@PathVariable Long id) {
        return categoryService.get(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<SuccessResponse<List<CategoryDto>>> getAll() {
        return categoryService.getAll();
    }
}
