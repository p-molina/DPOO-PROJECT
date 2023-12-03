package Bussines.Entities;

public class Product {
    private String name;
    private String brand;
    private double mrp;
    private Category category;
    private Review[] reviews;

    public Product(String name, String brand, double mrp, Category category) {
        this.name = name;
        this.mrp = mrp;
        this.brand = brand;
        this.category = category;
        this.reviews = null;
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

    public Category getCategory() {
        return category;
    }

    public Review[] getReviews() {
        return reviews;
    }

}
