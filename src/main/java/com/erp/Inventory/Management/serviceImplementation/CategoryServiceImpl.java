package com.erp.Inventory.Management.serviceImplementation;

import com.erp.Inventory.Management.dto.CategoryDto;
import com.erp.Inventory.Management.dto.SuccessResponse;
import com.erp.Inventory.Management.model.Category;
import com.erp.Inventory.Management.repository.CategoryRepository;
import com.erp.Inventory.Management.service.CategoryService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<SuccessResponse<CategoryDto>> create(CategoryDto dto) {
        Category category = new Category();
        category.setCategoryName(dto.getCategoryName());

        Category savedCategory = categoryRepository.save(category);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(savedCategory.getId());
        categoryDto.setCategoryName(savedCategory.getCategoryName());

        return ResponseEntity.ok(new SuccessResponse<>(200, "Category created", categoryDto));
    }

    @Override
    public ResponseEntity<SuccessResponse<CategoryDto>> update(Integer id, CategoryDto dto) {
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(new SuccessResponse<>(400, "Category not found", null));
        }

        Category category = categoryOpt.get();
        category.setCategoryName(dto.getCategoryName());

        Category updatedCategory = categoryRepository.save(category);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(updatedCategory.getId());
        categoryDto.setCategoryName(updatedCategory.getCategoryName());

        return ResponseEntity.ok(new SuccessResponse<>(200, "Category updated", categoryDto));
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> delete(Integer id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok(new SuccessResponse<>(200, "Category deleted", "Deleted successfully"));
    }

    @Override
    public ResponseEntity<SuccessResponse<CategoryDto>> get(Integer id) {
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        return categoryOpt.map(category -> {
                    CategoryDto categoryDto = new CategoryDto();
                    categoryDto.setId(category.getId());
                    categoryDto.setCategoryName(category.getCategoryName());
                    return ResponseEntity.ok(new SuccessResponse<>(200, "Category found", categoryDto));
                })
                .orElseGet(() -> ResponseEntity.badRequest().body(new SuccessResponse<>(400, "Category not found", null)));
    }

    @Override
    public ResponseEntity<SuccessResponse<List<CategoryDto>>> getAll() {
        List<CategoryDto> categories = categoryRepository.findAll().stream()
                .map(category -> {
                    CategoryDto categoryDto = new CategoryDto();
                    categoryDto.setId(category.getId());
                    categoryDto.setCategoryName(category.getCategoryName());
                    return categoryDto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(new SuccessResponse<>(200, "All categories", categories));
    }
}
