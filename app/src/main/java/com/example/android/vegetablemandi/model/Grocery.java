package com.example.android.vegetablemandi.model;

public class Grocery {
    int id;
    String name;
    String unit;
    String logo;
    int minimum_quantity;
    double price;
    String icon;
    String formatted_price;
    int category_id;
    Category category;


    public Grocery(String name, String logo, int minimum_quantity, String formatted_price, Category category) {
        this.name = name;
        this.logo = logo;
        this.minimum_quantity = minimum_quantity;
        this.formatted_price = formatted_price;
        this.category = category;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public String getUnit() {
        return unit.toLowerCase();
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getMinimum_quantity() {
        return minimum_quantity;
    }

    public void setMinimum_quantity(int minimum_quantity) {
        this.minimum_quantity = minimum_quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFormatted_price() {
        return formatted_price;
    }

    public void setFormatted_price(String formatted_price) {
        this.formatted_price = formatted_price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
