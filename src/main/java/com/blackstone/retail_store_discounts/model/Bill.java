package com.blackstone.retail_store_discounts.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "bill")
public class Bill {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column
    private Double totalAmount;


    @Column
    private Double netAmount;

    @Column
    private String productIds;

    @Transient
    private List<Product> products;

    public List<Product> getProducts() {
        return products == null ? null : new ArrayList<>(products);
    }


    public void setProducts(List<Product> products) {
        this.products = products == null ? null : new ArrayList<>(products);
    }


    public Users getUser() {
        return user == null ? null : new Users(user);
    }


    public void setUser(Users user) {
        this.user = user == null ? null : new Users(user);
    }




}
