package Presentation;

import Bussines.Entities.Category;
import Bussines.ProductManager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ProductController {
    private ProductManager productManager;

    public ProductController(ProductManager productManager) {
            this.productManager = productManager;
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
        String brand = UI.askForString("Please enter the product’s brand: ");
        float mrp = UI.askForFloat("Please enter the product’s maximum retail price [Please! use ',']: ", 0);
        mrp = Math.round(mrp * 100.0f) / 100.0f; // Se redondea los dos decimales

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

    }
}
