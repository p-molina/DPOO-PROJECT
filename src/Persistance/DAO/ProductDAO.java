package Persistance.DAO;

import Bussines.Entities.Product;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Interfaz para el acceso a datos (DAO) de los productos.
 */
public interface ProductDAO {
    /**
     * Verifica la existencia del archivo de productos.
     *
     * @throws FileNotFoundException Si el archivo no se encuentra.
     */
    void checkFile() throws FileNotFoundException;

    /**
     * Guarda todos los productos en el archivo JSON.
     *
     * @param products Lista de productos a guardar.
     * @throws IOException Si ocurre un error de entrada/salida al escribir en el archivo.
     */
    void saveAllProduct(List<Product> products) throws IOException;

    /**
     * Recupera todos los productos del archivo JSON.
     *
     * @return Lista de todos los productos.
     * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
     */
    List<Product> getAllProducts() throws IOException;
}
