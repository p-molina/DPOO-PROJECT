package Bussines.Entities;

public class CatalogProduct {
    private String nameProduct;
    private double price;

    public CatalogProduct(String nameProduct, double price) {
        this.nameProduct = nameProduct;
        this.price = price;
    }

    public String getNameProduct() {
        return nameProduct;
    }
    public double getPrice() {
        return price;
    }
}
