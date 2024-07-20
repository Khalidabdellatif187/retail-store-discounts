package com.blackstone.retail_store_discounts.repository;

import com.blackstone.retail_store_discounts.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Long> {
}
