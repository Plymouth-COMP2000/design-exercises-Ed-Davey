package com.example.restaurant_app.models;

public class MenuItem {
    private int id;
    private String name;
    private double price;
    private String imagePath;

    public MenuItem(int id, String name, double price, String imagePath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }

    public MenuItem(String name, double price, String imagePath) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getImagePath() { return imagePath; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}