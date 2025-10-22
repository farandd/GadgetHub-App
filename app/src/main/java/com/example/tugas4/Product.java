package com.example.tugas4;

public class Product {
    private String name;
    private String price;
    private int stock;
    private String category;
    private int imageResource;

    public Product(String name, String price, int stock, String category, int imageResource) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.imageResource = imageResource;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getCategory() {
        return category;
    }

    public int getImageResource() {
        return imageResource;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}