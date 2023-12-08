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

    private ProductManager productManager;
    private ShopDAO shopDAO;
    private ProductDAO productDAO;//TODO ELIMINAR

    public ShopManager(){}
    public ShopManager(ShopDAO shopDAO, ProductDAO productDAO) {
        this.shopDAO = shopDAO;
        this.productDAO = productDAO;
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

    public double getMRPFromProduct(String name) throws IOException {//TODO posar funció al productmanager y cridarla desde el shop controller
        double mrp = -1;

        for (Product product : productDAO.getAllProducts()) {
            if (product.getName().equals(name)) {
                mrp = product.getMrp();
            }
        }

        return mrp;
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
    }
    public void deleteProductFromShops(String name) throws IOException {
        List<Shop> shops = shopDAO.getAllShops();

        for (Shop shop : shops) {
            if (shop.getCatalogProductList() != null) {
                List<CatalogProduct> catalogProductList = shop.getCatalogProductList();
                Iterator<CatalogProduct> iterator = catalogProductList.iterator();

                // Itera sobre la lista de productos usando el iterador
                while (iterator.hasNext()) {
                    CatalogProduct catalogProduct = iterator.next();

                    if (catalogProduct.getNameProduct().equals(name)) {
                        iterator.remove();
                    }
                }
            }
        }

        shopDAO.saveAllShops(shops);
    }
    public boolean checkProductName(String nameProduct) throws IOException {//TODO cridar desde el controler el productManager.checkName(nameProduct)
        boolean found = false;
        found = productManager.checkName(nameProduct);
        return found;
    }
}
