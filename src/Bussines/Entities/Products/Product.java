package Bussines.Entities.Products;

import Bussines.Entities.Review;
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
    /**
     * Calcula y devuelve la calificación promedio de las reseñas del producto.
     * Si el producto no tiene reseñas, retorna -1 para indicar esta situación.
     *
     * @return La calificación promedio de las reseñas del producto, o -1 si no hay reseñas.
     */
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
    /**
     * Método abstracto destinado a ser implementado por clases derivadas para calcular
     * el precio base imponible del producto.
     * Este precio puede variar dependiendo del tipo específico de producto y su categoría fiscal.
     *
     * @param price Precio actual del producto antes de aplicar cualquier ajuste fiscal.
     * @return El precio base imponible del producto después de aplicar las reglas fiscales pertinentes.
     */
    public abstract double calculateTaxBasePrice(double price);
}
