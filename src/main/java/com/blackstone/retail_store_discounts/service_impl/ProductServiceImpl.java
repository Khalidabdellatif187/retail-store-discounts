package com.blackstone.retail_store_discounts.service_impl;

import com.blackstone.retail_store_discounts.exceptions.ResourceNotFoundException;
import com.blackstone.retail_store_discounts.model.Product;
import com.blackstone.retail_store_discounts.repository.ProductRepository;
import com.blackstone.retail_store_discounts.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found With Id" + id));
        return product;
    }
}
