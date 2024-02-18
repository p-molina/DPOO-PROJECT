package Persistance.DAO;

import Bussines.Entities.Shop.Shop;
import java.io.IOException;
import java.util.List;

/**
 * Interfaz para el acceso a datos (DAO) de las tiendas, proporcionando operaciones para
 * verificar y crear archivos, recuperar, agregar y eliminar tiendas en el almacén de datos.
 * Facilita la abstracción de la lógica de persistencia específica para las tiendas, permitiendo
 * su almacenamiento y recuperación de un archivo JSON.
 */
public interface ShopDAO {

    /**
     * Verifica la existencia del archivo de tiendas en el sistema de archivos y lo crea si no existe.
     *
     * @throws IOException Si ocurre un error de entrada/salida al verificar o crear el archivo.
     */
    void checkFile() throws IOException;

    /**
     * Agrega una nueva tienda al almacén de datos. Actualiza el archivo "shops.json" con la nueva tienda.
     *
     * @param shop La tienda a ser agregada al almacén de datos. Debe ser no nula.
     * @throws IOException Si ocurre un error de entrada/salida durante la operación de agregar la tienda.
     *                     Esto puede incluir errores al escribir en el archivo de tiendas.
     */
    void createShop(Shop shop) throws IOException;

    /**
     * Recupera todas las tiendas almacenadas en el archivo JSON y las devuelve como una lista.
     *
     * @return Lista de todas las tiendas recuperadas del almacén de datos.
     * @throws IOException Si ocurre un error de entrada/salida al leer el archivo de tiendas.
     */
    List<Shop> getAllShops() throws IOException;

    /**
     * Elimina una tienda de la lista de tiendas basado en su posición y actualiza el archivo "shops.json".
     *
     * @param position La posición de la tienda en la lista que se va a eliminar.
     * @throws IOException Si ocurre un error de entrada/salida durante la operación de eliminación.
     *                     Esto puede suceder si el archivo no se puede leer o escribir correctamente.
     */
    void deleteShop(int position) throws IOException;
}
