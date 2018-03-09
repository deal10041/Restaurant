package com.gmail.deal10041.restaurant;

import java.io.Serializable;

/**
 * Created by Dylan Wellner on 6-3-2018.
 * Implements a model class for a menu item
 */

public class MenuItem implements Serializable{

    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private String category;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {

        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
