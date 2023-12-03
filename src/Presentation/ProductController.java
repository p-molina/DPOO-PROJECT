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
        int selection = ui.askForInt("Choose an option:",1, 3);
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
        String name = ui.askForString("Please enter the product’s name: ");
        String brand = ui.askForString("Please enter the product’s brand: ");
        float mrp = ui.askForFloat("Please enter the product’s maximum retail price: ", 0 , 10000);
        Category category = new Category(ui.askForString("Please pick the product’s category: "));

        productManager.createProduct(name, brand, mrp, category);
    }
}
