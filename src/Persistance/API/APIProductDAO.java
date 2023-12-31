package Persistance.API;

import Bussines.Entities.Product;
import Persistance.DAO.ProductDAO;
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
        this.gson = new GsonBuilder().setPrettyPrinting().create();
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
            //Funcion
            System.out.println("ERROR: Api problem --> " + e.getMessage() + "\nCause --> " + e.getCause());
            check = false;
        }

        return check;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAllProduct(List<Product> products) throws IOException { //Actualizamos la información del fichero
        if (checkInstanceAPI()) {
            apiConnector.postRequest("products", gson.toJson(products.get(products.size() - 1)));
        }

        try (FileWriter writer = new FileWriter(path.toFile())) {
            gson.toJson(products, writer);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> getAllProducts() throws IOException {
        if (!Files.exists(path) || path.toFile().length() == 0) {
            return new ArrayList<>();
        }
        Type productListType = new TypeToken<ArrayList<Product>>(){}.getType();
        try (Reader reader = new FileReader(path.toFile())) { //Utilizamos aqui el try para cerrar el reader correctamente
            return gson.fromJson(reader, productListType);
        }
    }
}
