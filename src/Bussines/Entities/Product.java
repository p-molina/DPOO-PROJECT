package Bussines.Entities;

public class Product {
    private String name;
    private String brand;
    private double mrp;
    private String category;
    private Review[] reviews;

    public Product(String name, String brand, double mrp, String category) {
        this.name = name;
        this.mrp = mrp;
        this.brand = brand;
        this.category = category;
        this.reviews = new Review[0];
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

    public Review[] getReviews() {
        return reviews;
    }

}
