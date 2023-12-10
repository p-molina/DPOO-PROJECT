package Bussines;

import Bussines.Entities.CatalogProduct;
import Bussines.Entities.ShoppingCart;

import java.util.List;

public class ShoppingCartManager {
    private ShoppingCart shoppingCart;

    public ShoppingCartManager() {
        this.shoppingCart = new ShoppingCart();
    }
    public void addProductToCard(CatalogProduct catalogProduct)
    {
        List<CatalogProduct> cardList = shoppingCart.getCatalogProductList();
        cardList.add(catalogProduct);
        shoppingCart.setCatalogProductList(cardList);
    }
    public String getCardInfo()
    {
        String cardInfo = "\nYour card contains the following items:";
        double totalBuyPrice = 0;
        List<CatalogProduct> cardList = shoppingCart.getCatalogProductList();

        for (CatalogProduct catalogProduct : cardList) {
            cardInfo = cardInfo + "\n\t- " + catalogProduct.getNameProduct() + " by " + catalogProduct.getNameBrand() + "\nPrice: " + catalogProduct.getPrice();
        }
        for (CatalogProduct catalogProduct : cardList) {
            totalBuyPrice = totalBuyPrice + catalogProduct.getPrice();
        }
        cardInfo = cardInfo + "\nTotal: " + totalBuyPrice;
        shoppingCart.setTotalBuyPrice(totalBuyPrice);
        return cardInfo;
    }
}
