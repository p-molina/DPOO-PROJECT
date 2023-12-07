package Bussines.Entities;

import Bussines.Entities.BusinessModel;
import Bussines.Entities.CatalogProduct;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private String name;
    private String description;
    private int since;
    private double earnings;
    private String businessModel;
    private List<CatalogProduct> catalogProductList;

    public Shop(String name, String description, int since, double earnings, String businessModel) {
        this.name = name;
        this.description = description;
        this.since = since;
        this.earnings = earnings;
        this.businessModel = businessModel;
        this.catalogProductList = new ArrayList<CatalogProduct>(0);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getSince() {
        return since;
    }

    public List<CatalogProduct> getCatalogProductList() {
        return catalogProductList;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }
}
