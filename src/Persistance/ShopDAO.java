package Persistance;

import Bussines.Entities.Shop;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ShopDAO {

    private final static Path path = Path.of("files/shops.json");
    public ShopDAO(){}

    public void checkFile() throws FileNotFoundException {
        File file = path.toFile();

        if (!file.exists()) {
            throw new FileNotFoundException("ERROR: The file \"shops.json\" was not found.");
        }
    }
    public static List<Shop> cargarTiendasDesdeArchivo() {
        List<Shop> listaTiendas = new ArrayList<>();
        try {
            String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivoTiendas)));
            JSONArray jsonTiendas = new JSONArray(contenido);
            for (int i = 0; i < jsonTiendas.length(); i++) {
                JSONObject jsonTienda = jsonTiendas.getJSONObject(i);
                Shop tienda = Shop.shopFromJSON(jsonTienda);
                listaTiendas.add(tienda);
            }
        } catch (IOException | JSONException x) {
            //dotheexeption
        }
        return listaTiendas;
    }
    public static List<Shop> readAll() {
        return cargarTiendasDesdeArchivo();
    }

    public static void guardarTiendasEnArchivo(List<Shop> tiendas) {
        JSONArray jsonTiendas = new JSONArray();
        for (Shop tienda : tiendas) {
            jsonTiendas.put(tienda.shopToJSON());
        }
        try {
            Files.write(Paths.get(rutaArchivoTiendas), jsonTiendas.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addShopToFile(Shop tienda) {
        List<Shop> tiendas = cargarTiendasDesdeArchivo();
        tiendas.add(tienda);
        guardarTiendasEnArchivo(tiendas);
    }

    public static Shop findByName(String name) {
        List<Shop> tiendas = cargarTiendasDesdeArchivo();
        for (Shop tienda : tiendas) {
            if (tienda.getName().equals(name)) {
                return tienda;
            }
        }
        return null;
    }
}
