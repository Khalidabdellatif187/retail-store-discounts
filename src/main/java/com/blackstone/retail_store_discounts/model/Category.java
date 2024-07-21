package com.blackstone.retail_store_discounts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public Category(Category other) {
        this.id = other.id;
        this.name = other.name;
    }

    public Category() {
    }

}
