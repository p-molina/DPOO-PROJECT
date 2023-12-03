package Persistance;

import Bussines.Entities.Product;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProductDAO {
    private final static Path path = Path.of("files/products.json");
    private Gson gson = new Gson();
    public ProductDAO() {}

    public void checkFile() throws FileNotFoundException {
        File file = path.toFile();

        if (!file.exists()) {
            throw new FileNotFoundException("ERROR: The file \"products.json\" was not found.");
        }
    }

    public void addProductToFile(Product product) {
        File file = path.toFile();

        try {
            JSONArray productArray;
            if (file.exists() && file.length() != 0) {
                String content = new String(Files.readAllBytes(path));
                productArray = new JSONArray(content);
            } else {
                productArray = new JSONArray(); // Crear un JSONArray vacío si el archivo no existe o está vacío
            }

            JSONObject productJson = new JSONObject()
                    .put("name", product.getName())
                    .put("brand", product.getBrand())
                    .put("mrp", product.getMrp())
                    .put("category", product.getCategory().getCategory())
                    .put("reviews", new JSONArray()); //Se crea un nuevo JSONArray ya que cuando se crea el producto este campo es nulo
            productArray.put(productJson);

            // Escribir el array completo en el fichero
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(productArray.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
