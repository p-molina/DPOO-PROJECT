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
     * Recupera todos los productos del archivo JSON.
     *
     * @return Lista de todos los productos.
     * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
     */
    List<Product> getAllProducts() throws IOException;

    /**
     * Agrega un nuevo producto a la lista de productos y actualiza el archivo "products.json".
     * Primero, si la instancia de API está disponible, realiza una petición POST para agregar el producto.
     * Luego, lee la lista existente de productos del archivo JSON, agrega el nuevo producto a esta lista,
     * y finalmente escribe la lista actualizada de vuelta al archivo JSON.
     *
     * @param product El producto a agregar a la lista. No debe ser {@code null}.
     * @throws IOException Si ocurre un error de entrada/salida durante la lectura o escritura del archivo.
     *                     Esto puede suceder si el archivo no se puede abrir, leer o escribir.
     */
    void createProduct(Product product) throws IOException;

    void deleteProduct(int position) throws IOException;
}
