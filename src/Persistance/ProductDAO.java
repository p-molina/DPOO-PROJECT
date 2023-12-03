package Persistance;

import Bussines.Entities.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;

public class ProductDAO {
    private final static Path path = Path.of("files/products.json");
    public ProductDAO() {}

    public void checkFile() throws FileNotFoundException {
        File file = path.toFile();

        if (!file.exists()) {
            throw new FileNotFoundException("ERROR: The file \"products.json\" was not found.");
        }
    }

    public void addProductToFile(Product product) {
    }
}
