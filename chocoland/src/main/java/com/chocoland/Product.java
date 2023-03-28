package com.chocoland;


public class Product {
    
    int id;
    String name;
    String category;
    String description;
    double cost;
    String image;
    String brand;
    public Product(int id, String name, String category, String description, double cost, String image, String brand)
    {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.cost = cost;
        this.image = image;
        this.brand = brand;
    }

    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }

    public String getCategory()
    {
        return category;
    }


    public double getCost()
    {
        return cost;
    }
    public String getImage()
    {
        return image;
    }
    public String getBrand()
    {
        return brand;
    }
}

