package Presentation;

import java.io.FileNotFoundException;

/**
 * Clase que representa el menú principal de la aplicación.
 */
public class Menu {
    private ProductController productController;
    private ShopController shopController;
    private CartController cartController;

    /**
     * Constructor para inicializar el menú con los controladores correspondientes.
     *
     * @param productController Controlador de productos.
     * @param shopController    Controlador de tiendas.
     * @param cartController    Controlador de carrito de compras.
     */
    public Menu(ProductController productController, ShopController shopController, CartController cartController) {
        this.productController = productController;
        this.shopController = shopController;
        this.cartController = cartController;
    }
    /**
     * Ejecuta el flujo principal del menú de la aplicación.
     */
    public void run() {
        UI.showMenu(MenuOptions.MENU_PRINCIPAL);
        UI.showMenu(MenuOptions.CHECKING_FILES);

        if (checkPersistance()) {
            UI.showMenu(MenuOptions.START_PROGRAM);
            while(true) {
                UI.showMenu(MenuOptions.MAIN_MENU);
                int option = UI.askForOption("Choose a Digital Shopping Experience: ", 1, 6);

                switch(option) {
                    case 1:
                        manageProduct();
                        break;
                    case 2:
                        manageShop();
                        break;
                    case 3:
                        searchProduct();
                        break;
                    case 4:
                        listShops();
                        break;
                    case 5:
                        manageShoppingCart();
                        break;
                    case 6:
                        UI.showMenu(MenuOptions.EXIT_PROGRAM);
                        UI.closeScanner();
                        return; //We close the infinite loop and close the program and the execution.
                }
            }
        } else {
            UI.showMenu(MenuOptions.EXIT_PROGRAM);
        }
    }
    private boolean checkPersistance() {
        boolean check = false;

        try {
            productController.checkProductFile();
            shopController.checkFile();
            check = true;
        } catch (FileNotFoundException e){
            UI.showMessage(e.getMessage());
        }

        return check;
    }
    private void manageProduct() {
        productController.runProductMenu();}
    private void manageShop()
    {
        shopController.runShopMenu();
    }
    private void listShops()
    {
        shopController.listShops();
    }
    private void searchProduct()
    {
        productController.runSearchMenu();
    }
    private void manageShoppingCart()
    {
        cartController.runManageCard();
    }
}
