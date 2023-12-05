package Persistance;

import Bussines.Entities.Shop;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ShopDAO {

    private final static Path path = Path.of("files/shops.json");
    public ShopDAO(){}
    public static void checkFile() throws FileNotFoundException {
        File file = path.toFile();

        if (!file.exists()) {
            try {
                file.createNewFile(); // Crear el archivo si NO existe
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveAllShops(List<Shop> shops) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter fileWriter = new FileWriter("files/shops.json")) {
            gson.toJson(shops, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addShop(Shop shop) {
        try {
            checkFile(); // Comprobar si el archivo existe
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Shop> existingShops = loadAllShops();
        existingShops.add(shop);
        saveAllShops(existingShops);
    }
    private static List<Shop> loadAllShops() {
        List<Shop> shops = new ArrayList<>();

        try {
            File file = path.toFile();
            if (file.exists()) {
                Gson gson = new Gson();
                Type shopListType = new TypeToken<ArrayList<Shop>>() {}.getType();

                shops = gson.fromJson(new FileReader(file), shopListType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return shops;
    }
    public static Shop findByName(String name) {
        List<Shop> shops = loadAllShops();

        for (Shop shop : shops) {
            if (shop.getName().equals(name)) {
                return shop;
            }
        }
        return null;
    }
    public static boolean deleteShop(String name) {
        Shop shopToRemove = findByName(name);

        if (shopToRemove != null) {
            List<Shop> existingShops = loadAllShops();
            existingShops.remove(shopToRemove);
            saveAllShops(existingShops);
        } else {
            return false;
        }
        return true;
    }


}
