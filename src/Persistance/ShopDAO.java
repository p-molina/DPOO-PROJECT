package Persistance;

import Bussines.Entities.Product;
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

    public void checkFile() throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    public void saveAllShops(List<Shop> shops) throws IOException {
        try (FileWriter fileWriter = new FileWriter(path.toFile())) {
            gson.toJson(shops, fileWriter);
        }
    }

    public List<Shop> getAllShops() throws IOException {
        if (!Files.exists(path) || path.toFile().length() == 0) {
            return new ArrayList<>();
        }

        Type shopListType = new TypeToken<ArrayList<Shop>>(){}.getType();
        try (Reader reader = new FileReader(path.toFile())) { //Utilizamos aqui el try para cerrar el reader correctamente
            return gson.fromJson(reader, shopListType);
        }
    }

}
