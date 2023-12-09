package Bussines.Entities;

public class CatalogProduct {
    private String nameProduct;
    private String nameBrand;
    private double price;

    public CatalogProduct(String nameProduct, String nameBrand, double price) {
        this.nameProduct = nameProduct;
        this.nameBrand = nameBrand;
        this.price = price;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public double getPrice() {
        return price;
    }
}
