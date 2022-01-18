package com.example.demo.model;


import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.util.Set;

@Entity
@Data
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor

public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private Long price;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "USER_ID")
    private Long userCreate;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "product_categories",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID")})
    private Set<Category> categories;

}
