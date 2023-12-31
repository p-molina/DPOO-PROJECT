package Presentation;

import Bussines.ShoppingCartManager;

/**
 * Controlador para la gestión del carrito de compras en la interfaz de usuario.
 */
public class CartController {
    private ShoppingCartManager shoppingCartManager;

    /**
     * Constructor para inicializar el controlador del carrito de compras.
     *
     * @param shoppingCartManager Gestor del carrito de compras.
     */
    public CartController(ShoppingCartManager shoppingCartManager)
    {
        this.shoppingCartManager = shoppingCartManager;
    }
    /**
     * Ejecuta la gestión del carrito de compras, mostrando su contenido y precio total.
     */
    public void runManageCard()
    {
        String cardInfo = shoppingCartManager.getCardInfo();
        UI.showMessage(cardInfo);
        UI.showMenu(MenuOptions.MENU_CARD);
        int option = UI.askForOption("\nChoose an option:", 1,3);
        switch (option)
        {
            case 1:
                checkoutCard();
                break;
            case 2:
                clearCard();
                break;
        }
    }
    private void clearCard()
    {
        String confirm = UI.askForString("\nAre you sure you want to checkout? ");
        if(confirm.equals("YES") || confirm.equals("yes"))
        {
            shoppingCartManager.clearCard();
            UI.showMessage("\nYour cart has been cleared.");
        }

    }
    private void checkoutCard()
    {
        //TODO crear funcio al shopping card manager que retorni string amb la info de les ventes y actualitzi els ingresos de les tendes

        clearCard();
    }
}
