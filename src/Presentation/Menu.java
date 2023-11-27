package Presentation;

import java.io.FileNotFoundException;

public class Menu {
    private ProductController productController;
    private ShopController shopController;
    private CartController cartController;
    private UI ui;

    public Menu(ProductController productController, ShopController shopController, CartController cartController) {
        this.productController = productController;
        this.shopController = shopController;
        this. cartController = cartController;
        this.ui = new UI();
    }

    public void run() {
        ui.showMenu(MenuOptions.MENU_PRINCIPAL);
        ui.showMenu(MenuOptions.CHECKING_FILES);

        if (checkPersistance()) {
            ui.showMenu(MenuOptions.START_PROGRAM);
        } else {
            ui.showMenu(MenuOptions.EXIT_PROGRAM);
        }
    }
    private boolean checkPersistance() {
        boolean check = false;

        try {
            productController.checkProductFile();
            check = true;
        } catch (FileNotFoundException e){
            ui.showMessage(e.getMessage());
        }

        return check;
    }
}
