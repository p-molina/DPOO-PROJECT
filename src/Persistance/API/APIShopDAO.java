package Persistance.API;

import Bussines.Entities.Shop;
import Persistance.DAO.ShopDAO;
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
 * Clase para el acceso a datos (DAO) de las tiendas.
 */
public class APIShopDAO implements ShopDAO {
    private static final Path path = Path.of("files/shops.json");
    private Gson gson;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAllShops(List<Shop> shops) throws IOException {
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
