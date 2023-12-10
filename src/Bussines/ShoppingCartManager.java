package Bussines;

import Bussines.Entities.CatalogProduct;
import Bussines.Entities.ShoppingCart;

import java.util.List;

public class ShoppingCartManager {
    private ShoppingCart shoppingCart;

    public ShoppingCartManager() {
        this.shoppingCart = new ShoppingCart(0);
    }
    public void addProductToCard(CatalogProduct catalogProduct)
    {
        List<CatalogProduct> cardList = shoppingCart.getCatalogProductList();
        cardList.add(catalogProduct);
        shoppingCart.setCatalogProductList(cardList);
    }
}
