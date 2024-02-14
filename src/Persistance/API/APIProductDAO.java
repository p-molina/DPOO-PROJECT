package Persistance.API;

import Bussines.Entities.Product;
import Persistance.DAO.ProductDAO;
import Persistance.ProductDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.salle.url.api.exception.ApiException;

import java.io.*;
import java.lang.reflect.Type;
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
        if (checkInstanceAPI()) {
            return Product.toProductList(apiConnector.getRequest("products"));
        }

        if (!Files.exists(path) || path.toFile().length() == 0) {
            return new ArrayList<>();
        }
        Type productListType = new TypeToken<ArrayList<Product>>(){}.getType();//ERROR
        try (Reader reader = new FileReader(path.toFile())) { //Utilizamos aqui el try para cerrar el reader correctamente
            return gson.fromJson(reader, productListType);
        }
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
