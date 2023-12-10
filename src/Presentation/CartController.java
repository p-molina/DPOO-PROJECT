package Presentation;

import Bussines.ShoppingCartManager;

public class CartController {
    private ShoppingCartManager shoppingCartManager;
    public CartController(ShoppingCartManager shoppingCartManager)
    {
        this.shoppingCartManager = shoppingCartManager;
    }
    public void runManageCard()
    {
        String cardInfo = shoppingCartManager.getCardInfo();
        UI.showMessage(cardInfo);
    }
}
