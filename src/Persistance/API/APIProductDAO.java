package Persistance.API;

import Bussines.Entities.Products.Product;
import Persistance.DAO.ProductDAO;
import Persistance.ProductDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import edu.salle.url.api.exception.ApiException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para el acceso a datos (DAO) de los productos.
 */
public class APIProductDAO implements ProductDAO {
    private final static Path path = Path.of("files/products.json"); //Esto se tiene que modificar
    private Gson gson;
    private APIConnector apiConnector;

    /**
     * Constructor para inicializar el DAO de productos.
     */
    public APIProductDAO() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Product.class, new ProductDeserializer())
                .create();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkFile() throws FileNotFoundException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("ERROR: The file \"products.json\" was not found.");
        }
    }

    private boolean checkInstanceAPI() {
        boolean check;
        try {
            apiConnector = APIConnector.getInstance();
            check = true;
        } catch (ApiException e) {
            check = false;
        }

        return check;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> getAllProducts() throws IOException {
        List<Product> products = new ArrayList<>();

        if (checkInstanceAPI()) {
            String jsonResponse = apiConnector.getRequest("products");
            // Verificar si la respuesta es nula o vacía antes de parsear
            if (jsonResponse == null || jsonResponse.isEmpty()) {
                return products; // Devuelve lista vacía si no hay respuesta
            }
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(jsonResponse);
            if (jsonElement != null && jsonElement.isJsonArray()) {
                for (JsonElement element : jsonElement.getAsJsonArray()) {
                    Product product = gson.fromJson(element, Product.class);
                    products.add(product);
                }
            }
        } else if (!Files.exists(path) || path.toFile().length() == 0) {
            return products; // Devuelve lista vacía si el archivo no existe o está vacío
        } else {
            try (Reader reader = new FileReader(path.toFile())) {
                JsonParser parser = new JsonParser();
                JsonElement jsonElement = parser.parse(reader);
                // Comprueba si jsonElement es un array después de parsear el archivo
                if (jsonElement != null && jsonElement.isJsonArray()) {
                    for (JsonElement element : jsonElement.getAsJsonArray()) {
                        Product product = gson.fromJson(element, Product.class);
                        products.add(product);
                    }
                }
            }
        }

        return products;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void createProduct(Product product) throws IOException {
        if (checkInstanceAPI()) {
            apiConnector.postRequest("products", gson.toJson(product));
            //Se acaba la funcion ya que si no se guardaría en la nube y en el json
            return;
        }

        // Leer la lista existente de productos
        List<Product> products = getAllProducts();

        if (products == null) {
            products = new ArrayList<>();
        }

        // Añadir el nuevo producto a la lista
        products.add(product);

        // Guardar la lista actualizada en el archivo
        try (FileWriter writer = new FileWriter(path.toFile())) {
            gson.toJson(products, writer);
        }
    }

    public void deleteProduct(int position) throws IOException {
        if(checkInstanceAPI()){
            apiConnector.deleteRequest("products/" + position);
            //Se acaba la funcion ya que si no se guardaría en la nube y en el json
            return;
        }

        List<Product> products = getAllProducts();
        products.remove(position);

        try (FileWriter writer = new FileWriter(path.toFile())) {
            gson.toJson(products, writer);
        }
    }

}
