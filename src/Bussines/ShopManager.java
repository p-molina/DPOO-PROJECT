package Bussines;

import Bussines.Entities.BusinessModel;
import Bussines.Entities.Category;
import Bussines.Entities.Product;
import Bussines.Entities.Shop;
import Persistance.ShopDAO;

public class ShopManager {

    private ShopDAO shopDAO;

    public ShopManager(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }
    public void createShop(String name, String description , int since, String nameModel) {

        Shop shop = new Shop(name, description, since, 0, new BusinessModel(nameModel));

        ShopDAO.addShop(shop);
    }
    public boolean isShopUnique(String name)
    {
        Shop isUnique = ShopDAO.findByName(name);
        return isUnique == null;
    }
}
