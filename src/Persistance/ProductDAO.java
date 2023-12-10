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

/**
 * Classe para el acceso a datos (DAO) de los productos.
 */
public class ProductDAO {
    private final static Path path = Path.of("files/products.json");
    private Gson gson;

    /**
     * Constructor para inicializar el DAO de productos.
     */
    public ProductDAO() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    /**
     * Verifica la existencia del archivo de productos.
     *
     * @throws FileNotFoundException Si el archivo no se encuentra.
     */
    public void checkFile() throws FileNotFoundException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("ERROR: The file \"products.json\" was not found.");
        }
    }
    /**
     * Guarda todos los productos en el archivo JSON.
     *
     * @param products Lista de productos a guardar.
     * @throws IOException Si ocurre un error de entrada/salida al escribir en el archivo.
     */
    public void saveAllProduct(List<Product> products) throws IOException { //Actualizamos la información del fichero
        try (FileWriter writer = new FileWriter(path.toFile())) {
            gson.toJson(products, writer);
        }
    }
    /**
     * Recupera todos los productos del archivo JSON.
     *
     * @return Lista de todos los productos.
     * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
     */
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
