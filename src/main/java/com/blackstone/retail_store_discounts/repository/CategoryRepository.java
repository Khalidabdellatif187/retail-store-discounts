package com.blackstone.retail_store_discounts.repository;

import com.blackstone.retail_store_discounts.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {


}
