package Bussines;

import Bussines.Entities.Category;
import Bussines.Entities.Product;
import Persistance.ProductDAO;

import java.io.FileNotFoundException;
import java.io.IOException;

public class  ProductManager {
    private ProductDAO productDAO;

    public ProductManager(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void checkProductFile() throws FileNotFoundException {
        productDAO.checkFile();
    }

    public void createProduct(String name, String brand, double mrp, String category) throws IOException {
        Product product = new Product(name, brand, mrp, category);

        productDAO.addProductToFile(product);
    }

    public boolean checkName(String name) throws IOException {
        boolean found = false;

        for (Product product: productDAO.getAll()) {
            if (product.getName().equals(name)) {
                found = true;
                break;
            }
        }

        return found;
    }
}
