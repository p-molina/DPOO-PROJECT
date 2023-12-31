package Persistance.API;

import Bussines.Entities.Shop;
import Persistance.DAO.ShopDAO;
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
        this.gson = new GsonBuilder().setPrettyPrinting().create();
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
            check = false;
        }

        return check;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAllShops(List<Shop> shops) throws IOException {
        if (checkInstanceAPI()) {
            apiConnector = APIConnector.getInstance();
            apiConnector.postRequest("products", gson.toJson(shops.get(shops.size() - 1)));
        }

        //Si la instancia da error es que la api no va y estamos utilizando los ficheros locales
        try (FileWriter fileWriter = new FileWriter(path.toFile())) {
            gson.toJson(shops, fileWriter);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Shop> getAllShops() throws IOException {
        if (!Files.exists(path) || path.toFile().length() == 0) {
            return new ArrayList<>();
        }

        Type shopListType = new TypeToken<ArrayList<Shop>>(){}.getType();
        try (Reader reader = new FileReader(path.toFile())) {
            return gson.fromJson(reader, shopListType);
        }
    }
}
