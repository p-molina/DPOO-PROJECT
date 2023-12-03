package Presentation;

import Bussines.Entities.Category;
import Bussines.ProductManager;

import java.io.FileNotFoundException;

public class ProductController {
    private ProductManager productManager;
    private UI ui;

    public ProductController(ProductManager productManager) {
            this.productManager = productManager;
    }

    public void checkProductFile() throws FileNotFoundException {
        productManager.checkProductFile();
    }

    public void runProductMenu() {

        ui.showMenu(MenuOptions.MENU_PRODUCTO);
        int selection = ui.askForInt("Choose an option: ",1, 3);
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
        String name = ui.askForString("\nPlease enter the product’s name: ");
        String brand = ui.askForString("Please enter the product’s brand: ");
        float mrp = ui.askForFloat("Please enter the product’s maximum retail price [Please! use ',']: ", 0 , 10000);

        ui.showMenu(MenuOptions.SELECT_CATEGORY);
        int categorySelection = ui.askForInt("\nPlease pick the product’s category: ", 1, 3);

        if (categorySelection == 1) {
            productManager.createProduct(name, brand, mrp, new Category("GENERAL"));
        } else if (categorySelection == 2) {
            productManager.createProduct(name, brand, mrp, new Category("REDUCED"));
        } else if (categorySelection == 3)  {
            productManager.createProduct(name, brand, mrp, new Category("SUPER_REDUCED"));
        }

        ui.showMessage("The product \"" + name + "\" by \"" + brand + "\" was added to the system. \n");
    }
}
