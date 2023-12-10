package Bussines.Entities;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private String brand;
    private double mrp;
    private String category;
    private List<Review> reviews;

    public Product(String name, String brand, double mrp, String category) {
        this.name = name;
        this.mrp = mrp;
        this.brand = brand;
        this.category = category;
        this.reviews = new ArrayList<>(0);
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getMrp() {
        return mrp;
    }

    public String getCategory() {
        return category;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReview(List<Review> reviews) {
        this.reviews = reviews;
    }
}
