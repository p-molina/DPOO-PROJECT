package Persistance.API;

import Bussines.Entities.Shop.Shop;
import Persistance.DAO.ShopDAO;
import Persistance.ShopDeserializer;
import Persistance.ShopSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import edu.salle.url.api.exception.ApiException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para el acceso a datos (DAO) de las tiendas.
 */
public class APIShopDAO implements ShopDAO {
    private static final Path path = Path.of("files/shops.json");
    private Gson gson;
    private APIConnector apiConnector;

    /**
     * Constructor para inicializar el DAO de tiendas.
     */
    public APIShopDAO() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Shop.class, new ShopSerializer())
                .registerTypeAdapter(Shop.class, new ShopDeserializer())
                .setPrettyPrinting()
                .create();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkFile() throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }
    private boolean checkInstanceAPI() {
        boolean check;

        try {
            apiConnector = APIConnector.getInstance();
            check = true;
        } catch (ApiException e) {
            //Funcion
            //System.out.println("ERROR: Api problem --> " + e.getMessage() + "\nCause --> " + e.getCause());
            check = false;
        }

        return check;
    }

    public void createShop(Shop shop) throws IOException{
        if (checkInstanceAPI()) {
            apiConnector.postRequest("shops", gson.toJson(shop));
            System.out.println(gson.toJson(shop));
            return;
        }

        // Leer la lista existente de tienas
        List<Shop> shops = getAllShops();

        if (shops == null) {
            shops = new ArrayList<>();
        }

        // Añadir el nuevo producto a la lista
        shops.add(shop);

        // Guardar la lista actualizada en el archivo
        try (FileWriter writer = new FileWriter(path.toFile())) {
            gson.toJson(shops, writer);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Shop> getAllShops() throws IOException {
        List<Shop> shops = new ArrayList<>();

        if (checkInstanceAPI()) {
            String jsonResponse = apiConnector.getRequest("shops");
            // Verificar si la respuesta es nula o vacía antes de parsear
            if (jsonResponse == null || jsonResponse.isEmpty()) {
                return shops; // Devuelve lista vacía si no hay respuesta
            }
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(jsonResponse);
            if (jsonElement != null && jsonElement.isJsonArray()) {
                for (JsonElement element : jsonElement.getAsJsonArray()) {
                    Shop shop = gson.fromJson(element, Shop.class);
                    shops.add(shop);
                }
            }
        } else if (!Files.exists(path) || path.toFile().length() == 0) {
            return shops; // Devuelve lista vacía si el archivo no existe o está vacío
        } else {
            try (Reader reader = new FileReader(path.toFile())) {
                JsonParser parser = new JsonParser();
                JsonElement jsonElement = parser.parse(reader);
                // Comprueba si jsonElement es un array después de parsear el archivo
                if (jsonElement != null && jsonElement.isJsonArray()) {
                    for (JsonElement element : jsonElement.getAsJsonArray()) {
                        Shop shop = gson.fromJson(element, Shop.class);
                        shops.add(shop);
                    }
                }
            }
        }

        return shops;
    }

    @Override
    public void deleteShop(int position) throws IOException{
        if(checkInstanceAPI()) {
            apiConnector.deleteRequest("shops/" + (position));
        }

        // Leer la lista existente de tienas
        List<Shop> shops = getAllShops();

        if (shops == null) {
            shops = new ArrayList<>();
        }

        // Eliminar la antigua tienda de la lista
        shops.remove(position);

        // Guardar la lista actualizada en el archivo
        try (FileWriter writer = new FileWriter(path.toFile())) {
            gson.toJson(shops, writer);
        }
    }
}
