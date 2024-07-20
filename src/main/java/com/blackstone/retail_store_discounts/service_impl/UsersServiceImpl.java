package com.blackstone.retail_store_discounts.service_impl;


import com.blackstone.retail_store_discounts.exceptions.ResourceNotFoundException;
import com.blackstone.retail_store_discounts.model.Users;
import com.blackstone.retail_store_discounts.repository.UsersRepository;
import com.blackstone.retail_store_discounts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UserService {


    @Autowired
    private UsersRepository usersRepository;


    @Override
    public Users findUserById(Long id) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found With Id" + id));
        return user;
    }
}
