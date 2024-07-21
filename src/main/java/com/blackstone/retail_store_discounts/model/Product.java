package com.blackstone.retail_store_discounts.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;


    @Column
    private Double price;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public Category getCategory() {
        if (this.category == null) {
            return null;
        }
        Category copy = new Category();
        copy.setId(this.category.getId());
        copy.setName(this.category.getName());
        return copy;
    }

    // Modify the setter to store a defensive copy
    public void setCategory(Category category) {
        if (category == null) {
            this.category = null;
        } else {
            Category copy = new Category();
            copy.setId(category.getId());
            copy.setName(category.getName());
            this.category = copy;
        }
    }
}
