package Bussines;

import Persistance.ProductDAO;

import java.io.FileNotFoundException;

public class ProductManager {
    private ProductDAO productDAO;

    public ProductManager(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void checkProductFile() throws FileNotFoundException {
        productDAO.checkFile();
    }
}
