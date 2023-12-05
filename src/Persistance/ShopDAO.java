package Persistance;

import Bussines.Entities.Shop;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ShopDAO {

    private static final Path path = Path.of("files/shops.json");
    private Gson gson;

    public ShopDAO() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public static void checkFile() throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    public void saveAllShops(List<Shop> shops) throws IOException {
        try (FileWriter fileWriter = new FileWriter(path.toFile())) {
            gson.toJson(shops, fileWriter);
        }
    }

    public void addShop(Shop shop) throws IOException {
        checkFile(); // Comprobar si el archivo existe
        List<Shop> existingShops = loadAllShops();
        existingShops.add(shop);
        saveAllShops(existingShops);
    }

    private List<Shop> loadAllShops() throws IOException {
        if (!Files.exists(path) || Files.size(path) == 0) {
            return new ArrayList<>();
        }

        try (Reader reader = Files.newBufferedReader(path)) {
            Type shopListType = new TypeToken<ArrayList<Shop>>() {}.getType();
            return gson.fromJson(reader, shopListType);
        }
    }

    public Shop findByName(String name) throws IOException {
        List<Shop> shops = loadAllShops();
        for (Shop shop : shops) {
            if (shop.getName().equals(name)) {
                return shop;
            }
        }
        return null;
    }
}
