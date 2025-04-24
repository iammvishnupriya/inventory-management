package com.erp.Inventory.Management.repository;



import com.erp.Inventory.Management.model.Category;
import com.erp.Inventory.Management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
