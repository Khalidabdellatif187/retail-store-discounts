package com.blackstone.retail_store_discounts.repository;

import com.blackstone.retail_store_discounts.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
