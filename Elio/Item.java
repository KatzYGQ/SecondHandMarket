package com.codeuptopia.secondhandmarket.model;

import com.codeuptopia.secondhandmarket.model.User;

public class Item {
    private int id;
    private String name;
    private String description;
    private double price;
    private User seller;
    
    public Item(int id, String name, String description, double price, User seller) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.seller = seller;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public User getSeller() {
        return seller;
    }
    
    public void setSeller(User seller) {
        this.seller = seller;
    }
}
