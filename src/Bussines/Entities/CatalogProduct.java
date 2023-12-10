package Bussines.Entities;

public class CatalogProduct {
    private String nameProduct;
    private String nameBrand;
    private String nameShop;
    private double price;

    public CatalogProduct(String nameProduct, String nameBrand,String nameShop, double price) {
        this.nameProduct = nameProduct;
        this.nameBrand = nameBrand;
        this.nameShop = nameShop;
        this.price = price;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public String getNameShop() {
        return nameShop;
    }

    public double getPrice() {
        return price;
    }
}
