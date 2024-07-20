package com.blackstone.retail_store_discounts.model;


import com.blackstone.retail_store_discounts.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDate joinDate;

    @Column
    @Enumerated(EnumType.STRING)
    private UserType userType;


}
