package Bussines;

import Bussines.Entities.Category;
import Bussines.Entities.Product;
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

    public void createProduct(String name, String brand, double mrp, Category category) {
        Product product = new Product(name, brand, mrp, category);

        productDAO.addProductToFile(product);
    }
}
