package Bussines;

import java.util.List;

public class Shop {
    private String name;
    private String description;
    private int since;
    private double earnings;
    private BusinessModel businessModel;
    private List<CatalogProduct> catalogProductList;

    public Shop(String name, String description, int since, double earnings, BusinessModel businessModel, List<CatalogProduct> catalogProductList) {
        this.name = name;
        this.description = description;
        this.since = since;
        this.earnings = earnings;
        this.businessModel = businessModel;
        this.catalogProductList = catalogProductList;
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

    public void setBusinessModel(String businessModel)
    {
        this.businessModel = new BusinessModel(businessModel);
    }

    public void addCatalogProduct(String nameProduct, String nameShop, int price)
    {
        catalogProductList.add(new CatalogProduct(nameProduct,nameShop, price));
    }

    public void deleteCatalogProduct(String nameProduct)
    {
        //eliminar catalogProduct;
    }
}
