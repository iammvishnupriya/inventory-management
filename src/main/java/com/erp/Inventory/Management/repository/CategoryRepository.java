package com.erp.Inventory.Management.repository;



import com.erp.Inventory.Management.model.Category;
import com.erp.Inventory.Management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {



}
