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
            while(true) {
                ui.showMenu(MenuOptions.MAIN_MENU);
                int option = ui.askForInt("Choose a Digital Shopping Experience: ", 1, 6);

                switch(option) {
                    case 1:
                        menuProduct();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        ui.showMenu(MenuOptions.EXIT_PROGRAM);
                        return; //We close the infinite loop and close the program and the execution.
                }
            }
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
    private void menuProduct() {
        productController.runProductMenu();
    }
}
