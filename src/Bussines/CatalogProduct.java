package Bussines;

public class CatalogProduct {
    private String nameProduct;
    private String nameShop;
    private double price;

    public CatalogProduct(String nameProduct, String nameShop, double price) {
        this.nameProduct = nameProduct;
        this.nameShop = nameShop;
        this.price = price;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public String getNameShop() {
        return nameShop;
    }

    public double getPrice() {
        return price;
    }
}
