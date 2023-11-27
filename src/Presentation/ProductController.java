package Presentation;

import Bussines.ProductManager;

import java.io.FileNotFoundException;

public class ProductController {
    private ProductManager productManager;

    public ProductController(ProductManager productManager) {
            this.productManager = productManager;
    }

    public void checkProductFile() throws FileNotFoundException {
        productManager.checkProductFile();
    }
}
