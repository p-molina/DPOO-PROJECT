package Persistance;

import Bussines.Entities.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void checkFile() throws FileNotFoundException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("ERROR: The file \"products.json\" was not found.");
        }
    }

    public void addProductToFile(Product product) throws IOException{
        List<Product> products = getAll();
        products.add(product);
        saveProducts(products);
    }

    private void saveProducts(List<Product> products) throws IOException {
        FileWriter writer = new FileWriter(path.toFile());
        gson.toJson(products, writer);
        writer.close();
    }

    public List<Product> getAll() throws IOException {
        if (!Files.exists(path) || path.toFile().length() == 0) {
            return new ArrayList<>();
        }

        Type productListType = new TypeToken<ArrayList<Product>>(){}.getType();
        try (Reader reader = new FileReader(path.toFile())) { //Utilizamos aqui el try para cerrar el reader correctamente
            return gson.fromJson(reader, productListType);
        }
    }

}
