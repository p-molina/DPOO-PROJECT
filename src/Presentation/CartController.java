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
    }
}
