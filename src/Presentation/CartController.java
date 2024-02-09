package Presentation;

import Bussines.ProductManager;
import Bussines.ShopManager;
import Bussines.ShoppingCartManager;

import java.io.IOException;

/**
 * Controlador para la gestión del carrito de compras en la interfaz de usuario.
 */
public class CartController {
    private ShoppingCartManager shoppingCartManager;
    private ShopManager shopManager;
    private ProductManager productManager;

    /**
     * Constructor para inicializar el controlador del carrito de compras.
     *
     * @param shoppingCartManager Gestor del carrito de compras.
     */
    public CartController(ShoppingCartManager shoppingCartManager, ShopManager shopManager, ProductManager productManager) {
        this.shoppingCartManager = shoppingCartManager;
        this.shopManager = shopManager;
        this.productManager = productManager;
    }

    /**
     * Ejecuta la gestión del carrito de compras, mostrando su contenido y precio total.
     */
    public void runManageCard()
    {
        String cardInfo = shoppingCartManager.getCardInfo();
        UI.showMessage(cardInfo);
        UI.showMenu(MenuOptions.MENU_CARD);
        int option = UI.askForOption("\nChoose an option: ", 1,3);
        switch (option)
        {
            case 1:
                String confirm1 = UI.askForString("\nAre you sure you want to checkout? ");
                if(confirm1.equals("YES") || confirm1.equals("yes"))
                {
                    checkoutCard();
                }

                break;
            case 2:
                String confirm = UI.askForString("\nAre you sure you want to clear? ");
                if(confirm.equals("YES") || confirm.equals("yes"))
                {
                    clearCard();
                }
                break;
        }
    }
    private void clearCard()
    {
        shoppingCartManager.clearCard();
        UI.showMessage("\nYour cart has been cleared.");
    }
    private void checkoutCard()
    {
        try {
            UI.showMessage(shopManager.setNewIncomes(productManager.getTaxBasePrice(shoppingCartManager.getCheckoutCard())));
            clearCard();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
