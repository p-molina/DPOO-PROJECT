package Bussines.Entities;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CatalogProduct> catalogProductList;
    private double totalBuyPrice;

    public ShoppingCart() {
        this.catalogProductList = new ArrayList<CatalogProduct>(0);
        this.totalBuyPrice = 0;
    }

    public List<CatalogProduct> getCatalogProductList() {
        return catalogProductList;
    }

    public double getTotalBuyPrice() {
        return totalBuyPrice;
    }

    public void setCatalogProductList(List<CatalogProduct> catalogProductList) {
        this.catalogProductList = catalogProductList;
    }

    public void setTotalBuyPrice(double totalBuyPrice) {
        this.totalBuyPrice = totalBuyPrice;
    }
}
