package Bussines.Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un producto, incluyendo detalles como nombre, marca, precio máximo de venta al público (MRP), categoría y reseñas.
 */
public class Product {
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
    public Product(String name, String brand, double mrp, String category) {
        this.name = name;
        this.mrp = mrp;
        this.brand = brand;
        this.category = category;
        this.reviews = new ArrayList<>(0);
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
}
