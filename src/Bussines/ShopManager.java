package Bussines;

import Bussines.Entities.BusinessModel;
import Bussines.Entities.Shop;
import Persistance.ShopDAO;

import java.io.IOException;

public class ShopManager {

    private ShopDAO shopDAO;

    public ShopManager(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }
    public void createShop(String name, String description , int since, String nameModel) throws IOException {

        Shop shop = new Shop(name, description, since, 0, new BusinessModel(nameModel));

        shopDAO.addShop(shop);
    }
    public boolean isShopUnique(String name) throws IOException {
        Shop isUnique = shopDAO.findByName(name);
        return isUnique == null;
    }
}
