package Bussines;

import Bussines.Entities.BusinessModel;
import Bussines.Entities.CatalogProduct;
import Bussines.Entities.Product;
import Bussines.Entities.Shop;
import Persistance.ProductDAO;
import Persistance.ShopDAO;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ShopManager {
    private ShopDAO shopDAO;

    public ShopManager(){}
    public ShopManager(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }
    public void checkFile() throws IOException{
        shopDAO.checkFile();
    }

    public void createShop(String name, String description , int since, String nameModel) throws IOException {
        //BusinessModel businessModel = new BusinessModel(nameModel);
        Shop shop = new Shop(name, description, since, 0, nameModel);
        List<Shop> shops = shopDAO.getAllShops();
        shops.add(shop);
        shopDAO.saveAllShops(shops);
    }
    public boolean deleteShop(String name) throws IOException {
        Shop shopToRemove = findByName(name);

        if (shopToRemove != null) {
            List<Shop> existingShops = shopDAO.getAllShops();
            existingShops.remove(shopToRemove);
            shopDAO.saveAllShops(existingShops);
        } else {
            return false;
        }
        return true;
    }
    public boolean isShopUnique(String name) throws IOException {
        Shop isUnique = findByName(name);
        return isUnique == null;
    }
    public Shop findByName(String name) throws IOException {
        List<Shop> shops = shopDAO.getAllShops();

        for (Shop shop : shops) {
            if (shop.getName().equals(name)) {
                return shop;
            }
        }
        return null;
    }
    public void expandCatalogue(String nameShop, String nameProduct, double price) throws IOException {
        List<Shop> shops = shopDAO.getAllShops();
        for (Shop shop : shops) {
            if(shop.getName().equals(nameShop)) {
                if (shop.getCatalogProductList() != null) {
                    List<CatalogProduct> catalogProductList = shop.getCatalogProductList();
                    CatalogProduct newProduct = new CatalogProduct(nameProduct, price);
                    catalogProductList.add(newProduct);
                }
            }
        }
        shopDAO.saveAllShops(shops);
    }
    public void deleteProductFromShop(String nameProduct, String nameShop) throws IOException {
        List<Shop> shops = shopDAO.getAllShops();
        for (Shop shop : shops) {
            if (shop.getName().equals(nameShop)) {
                if (shop.getCatalogProductList() != null) {
                    List<CatalogProduct> catalogProductList = shop.getCatalogProductList();
                    Iterator<CatalogProduct> iterator = catalogProductList.iterator();
                    while (iterator.hasNext()) {
                        CatalogProduct product = iterator.next();
                        if (product.getNameProduct().equals(nameProduct)) {
                            iterator.remove(); // Eliminar el producto del catálogo
                            break; // Terminar el bucle al encontrar el producto
                        }
                    }
                    break; // Terminar la búsqueda al encontrar la tienda
                }
            }
        }
        shopDAO.saveAllShops(shops);
    }
    public void deleteProductFromAllShops(String name) throws IOException {
        List<Shop> shops = shopDAO.getAllShops();

        for (Shop shop : shops) {
            deleteProductFromShop(name, shop.getName());
        }
        shopDAO.saveAllShops(shops);
    }
    public boolean isProductInCatalogue(String nameProduct, String nameShop) throws IOException {
        List<Shop> shops = shopDAO.getAllShops();
        for (Shop shop : shops) {
            if (shop.getName().equals(nameShop)) {
                List<CatalogProduct> catalogProductList = shop.getCatalogProductList();
                if (catalogProductList != null) {
                    for (CatalogProduct product : catalogProductList) {
                        if (product.getNameProduct().equals(nameProduct)) {
                            return true; // El producto está en el catálogo
                        }
                    }
                }
                break; // Terminar la búsqueda al encontrar la tienda
            }
        }
        return false; // El producto no está en el catálogo o la tienda no fue encontrada
    }
    public String getCatalogueFromShop(String shopName) throws IOException {
        Shop shop = findByName(shopName);
        if(shop != null)
        {
            String message = "";
            List<CatalogProduct> catalogue = shop.getCatalogProductList();
            for (int i = 0; i < catalogue.size(); i++) {
                CatalogProduct product = catalogue.get(i);
                message = message + (i + 1) + ". " + product.getNameProduct() + "\n";
            }
            return message;
        }
        return null;
    }
    public boolean reduceCatalogue(String shopName, int productIndex) throws IOException {
        Shop shop = findByName(shopName);
        List<CatalogProduct> catalogue = shop.getCatalogProductList();
        if(productIndex != 0)
        {
            CatalogProduct selectedProduct = catalogue.get(productIndex - 1);
            String catalogueProductName = selectedProduct.getNameProduct();
            deleteProductFromShop(catalogueProductName,shopName);
            return true;
        }
        return false;
    }
}
