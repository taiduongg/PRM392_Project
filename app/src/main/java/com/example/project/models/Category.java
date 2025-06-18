package com.example.project.models;

import java.util.List;

public class Category {
    private String name;
    private int imageResId;
    private List<Product> products;

    public Category(String name, int imageResId, List<Product> products) {
        this.name = name;
        this.imageResId = imageResId;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public List<Product> getProducts() {
        return products;
    }
}
