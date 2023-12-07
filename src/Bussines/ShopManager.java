package Bussines;

import Bussines.Entities.BusinessModel;
import Bussines.Entities.Product;
import Bussines.Entities.Shop;
import Persistance.ProductDAO;
import Persistance.ShopDAO;

import java.io.IOException;
import java.util.List;

public class ShopManager {

    private ShopDAO shopDAO;
    private ProductDAO productDAO;

    public ShopManager(ShopDAO shopDAO, ProductDAO productDAO) {
        this.shopDAO = shopDAO;
        this.productDAO = productDAO;
    }
    public void checkFile() throws IOException{
        shopDAO.checkFile();
    }

    public void createShop(String name, String description , int since, String nameModel) throws IOException {
        Shop shop = new Shop(name, description, since, 0, new BusinessModel(nameModel));
        List<Shop> shops = shopDAO.getAllShops();
        shops.add(shop);
        shopDAO.addShop(shops);
    }
    public boolean isShopUnique(String name) throws IOException {
        Shop isUnique = shopDAO.findByName(name);
        return isUnique == null;
    }

    public double getMRPFromProduct(String name) throws IOException {
        double mrp = -1;

        for (Product product : productDAO.getAllProducts()) {
            if (product.getName().equals(name)) {
                mrp = product.getMrp();
            }
        }

        return mrp;
    }
    public void deleteProductFromShops(String name) throws IOException {
        shopDAO.getAllShops();
    }
}
