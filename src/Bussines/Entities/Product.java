package Bussines.Entities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un producto, incluyendo detalles como nombre, marca, precio máximo de venta al público (MRP), categoría y reseñas.
 */
public abstract class Product {
    private String name;
    private String brand;
    private double mrp;
    private String category;
    private List<Review> reviews;
    /**
     * Constructor para crear un producto.
     *
     * @param name     Nombre del producto.
     * @param brand    Marca del producto.
     * @param mrp      Precio máximo de venta al público.
     * @param category Categoría del producto.
     */
    public Product(String name, String brand, double mrp, String category, List<Review> reviews) {
        this.name = name;
        this.brand = brand;
        this.mrp = mrp;
        this.category = category;
        this.reviews = reviews;
    }
    /**
     * Obtiene el nombre del producto.
     *
     * @return Nombre del producto.
     */
    public String getName() {
        return name;
    }
    /**
     * Obtiene la marca del producto.
     *
     * @return Marca del producto.
     */
    public String getBrand() {
        return brand;
    }
    /**
     * Obtiene el precio máximo de venta al público (MRP) del producto.
     *
     * @return Precio máximo de venta al público.
     */
    public double getMrp() {
        return mrp;
    }
    /**
     * Obtiene la categoría del producto.
     *
     * @return Categoría del producto.
     */
    public String getCategory() {
        return category;
    }
    /**
     * Obtiene las reseñas del producto.
     *
     * @return Lista de reseñas del producto.
     */
    public List<Review> getReviews() {
        return reviews;
    }
    public double getAverageRating() {
        if (reviews == null || reviews.size() == 0) {
            return -1;
        }

        double sum = 0;
        for (Review review : reviews) {
            sum += review.getClassificationStars();
        }

        return sum / reviews.size();
    }
    public static List<Product> toProductList(String input) {
        Gson gson = new Gson();
        Type productListType = new TypeToken<ArrayList<Product>>(){}.getType();
        return gson.fromJson(input, productListType);
    }

    public abstract double calculateTaxBasePrice(double price);
}
