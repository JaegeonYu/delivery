package com.example.bio.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long price;
    private String brand;
    private String category;
}
