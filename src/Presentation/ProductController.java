package Presentation;

import Bussines.Entities.Category;
import Bussines.ProductManager;
import Bussines.ShopManager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ProductController {
    private ProductManager productManager;
    private ShopManager shopManager;

    public ProductController(ProductManager productManager, ShopManager shopManager) {

        this.productManager = productManager;
        this.shopManager = shopManager;
    }

    public void checkProductFile() throws FileNotFoundException {
        productManager.checkProductFile();
    }

    public void runProductMenu() {

        UI.showMenu(MenuOptions.MENU_PRODUCTO);
        int selection = UI.askForOption("Choose an option: ",1, 3);
        switch (selection) {
            case 1:
                createPorduct();
                break;
            case 2:
                deleteProduct();
                break;
            case 3:
                break;
        }
    }

    private void createPorduct() {
        String name = UI.askForString("\nPlease enter the product’s name: ");
        try {
            if (productManager.checkName(name)) {
                UI.showMessage("\nERROR: This product '" + name + "' already exists. Going back...");
                return;
            }
        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file!. Going back...");
            return;
        }
        String brand = productManager.toTitleCase(UI.askForString("Please enter the product’s brand: "));
        double mrp = UI.askForDouble("Please enter the product’s maximum retail price [Please! use ',']: ", 0);

        UI.showMenu(MenuOptions.SELECT_CATEGORY);
        int categorySelection = UI.askForOption("\nPlease pick the product’s category: ", 1, 3);
        try {
            if (categorySelection == 1) {
                productManager.createProduct(name, brand, mrp, "GENERAL");
            } else if (categorySelection == 2) {
                productManager.createProduct(name, brand, mrp, "REDUCED");
            } else if (categorySelection == 3) {
                productManager.createProduct(name, brand, mrp, "SUPER_REDUCED");
            }
        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file!. Going back...\n");
        }

        UI.showMessage("\nThe product \"" + name + "\" by \"" + brand + "\" was added to the system.");
    }

    private void deleteProduct() {
        try {
            UI.showListOfProducts(productManager.getProductsRatingMap());
        } catch (IOException e) {
            UI.showMessage("ERROR: Problem with the file!. Going back...");
            return;
        }

        UI.showMenu(MenuOptions.DELETE_MENU);
        if (UI.askForOption("\nChoose an option: ", 1, 2) == 1) {
            String name;
            while(true) {
                name = UI.askForString("\nPlease enter the product’s name to delete: ");
                try {
                    if (productManager.checkName(name)) {break;}
                    UI.showMessage("\nERROR: This product '" + name + "' does not exists. Try again...");
                } catch (IOException e) {
                    UI.showMessage("ERROR: Problem with the file!. Going back...");
                    break;
                }
            }
            String option = UI.askForString("Are you sure you want to remove \"" + name + "\"?");
            if(option.equalsIgnoreCase("yes")) {
                try {
                    productManager.deleteProduct(name);
                    shopManager.deleteProductFromShops(name);
                    UI.showMessage("\"" + name + "\" has been withdrawn from sale.");
                } catch (IOException e) {
                    UI.showMessage("ERROR: Problem with the file!. Going back...");
                }
            }
        }
    }
}
