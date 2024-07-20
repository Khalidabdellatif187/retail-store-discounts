package com.blackstone.retail_store_discounts.repository;

import com.blackstone.retail_store_discounts.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
}
