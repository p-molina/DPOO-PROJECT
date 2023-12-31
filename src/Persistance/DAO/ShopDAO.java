package Persistance.DAO;

import Bussines.Entities.Shop;
import java.io.IOException;
import java.util.List;

/**
 * Interfaz para el acceso a datos (DAO) de las tiendas.
 */
public interface ShopDAO {
    /**
     * Verifica y crea el archivo de tiendas si no existe.
     *
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    void checkFile() throws IOException;

    /**
     * Guarda todas las tiendas en el archivo JSON.
     *
     * @param shops Lista de tiendas a guardar.
     * @throws IOException Si ocurre un error de entrada/salida al escribir en el archivo.
     */
    void saveAllShops(List<Shop> shops) throws IOException;

    /**
     * Recupera todas las tiendas del archivo JSON.
     *
     * @return Lista de todas las tiendas.
     * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
     */
    List<Shop> getAllShops() throws IOException;
}
