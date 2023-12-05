package Persistance;

import Bussines.Entities.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final static Path path = Path.of("files/products.json");
    private Gson gson;

    public ProductDAO() {
        this.gson = new Gson();
    }

    public void checkFile() throws FileNotFoundException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("ERROR: The file \"products.json\" was not found.");
        }
    }

    public void addProductToFile(Product product) {
        List<Product> products = getAll();
        products.add(product);
        saveProducts(products);
    }

    private void saveProducts(List<Product> products) {
        try (FileWriter writer = new FileWriter(path.toFile())) {
            gson.toJson(products, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAll() {
        if (!Files.exists(path) || path.toFile().length() == 0) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(path.toFile())) {
            Type productListType = new TypeToken<ArrayList<Product>>(){}.getType();
            return gson.fromJson(reader, productListType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
