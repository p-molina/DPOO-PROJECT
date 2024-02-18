package Persistance.DAO;

import Bussines.Entities.Products.Product;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Interfaz para el acceso a datos (DAO) de productos, proporcionando operaciones para
 * verificar la existencia de archivos, recuperar, agregar y eliminar productos en el almacén de datos.
 * Se espera que las implementaciones manejen la persistencia de productos, típicamente en un archivo JSON.
 */
public interface ProductDAO {

    /**
     * Verifica la existencia del archivo de productos en el sistema de archivos.
     *
     * @throws FileNotFoundException Si el archivo especificado para los productos no se encuentra.
     */
    void checkFile() throws FileNotFoundException;

    /**
     * Recupera todos los productos almacenados en el archivo JSON y los devuelve como una lista.
     *
     * @return Lista de todos los productos recuperados del almacén de datos.
     * @throws IOException Si ocurre un error de entrada/salida al leer el archivo de productos.
     */
    List<Product> getAllProducts() throws IOException;

    /**
     * Agrega un nuevo producto al almacén de datos. Si es posible, realiza una operación de creación
     * en una API remota y luego actualiza el archivo local "products.json" con el nuevo producto.
     *
     * @param product El producto a ser agregado al almacén de datos. Debe ser no nulo.
     * @throws IOException Si ocurre un error de entrada/salida durante la operación. Esto puede incluir errores
     *                     al leer o escribir en el archivo de productos, así como problemas al comunicarse con
     *                     una posible API remota para la operación de creación.
     */
    void createProduct(Product product) throws IOException;

    /**
     * Elimina un producto de la lista de productos basado en su posición y actualiza el archivo "products.json".
     *
     * @param position La posición del producto en la lista que se va a eliminar.
     * @throws IOException Si ocurre un error de entrada/salida durante la operación de eliminación.
     *                     Esto puede suceder si el archivo no se puede leer o escribir correctamente.
     */
    void deleteProduct(int position) throws IOException;
}
