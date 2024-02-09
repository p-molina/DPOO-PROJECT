package Persistance.DAO;

import Bussines.Entities.Shop.Shop;
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

    void createShop(Shop shop) throws IOException;

    /**
     * Recupera todas las tiendas del archivo JSON.
     *
     * @return Lista de todas las tiendas.
     * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
     */
    List<Shop> getAllShops() throws IOException;
    void deleteShop(int position) throws IOException;
}
