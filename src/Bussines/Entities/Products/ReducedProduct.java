package Bussines.Entities.Products;

import Bussines.Entities.Review;

import java.util.List;

/**
 * Representa un producto con un régimen fiscal reducido. Este tipo de producto se beneficia
 * de tasas de impuestos menores en comparación con productos estándar, influenciado por su
 * calificación promedio de reseñas.
 */
public class ReducedProduct extends Product {
    /**
     * Constructor para crear un producto reducido.
     *
     * @param name     Nombre del producto.
     * @param brand    Marca del producto.
     * @param mrp      Precio máximo de venta al público (MRP).
     * @param category Categoría del producto.
     * @param reviews  Lista de reseñas asociadas al producto.
     */
    public ReducedProduct(String name, String brand, double mrp, String category, List<Review> reviews) {
        super(name, brand, mrp, category, reviews);
    }

    /**
     * Calcula el precio base imponible del producto aplicando la tasa de impuestos reducida.
     * La tasa aplicada depende de la calificación promedio de las reseñas del producto. Si la
     * calificación promedio es mayor a 3.5, se aplica una tasa de impuestos del 5%; de lo contrario,
     * la tasa es del 10%.
     *
     * @param price Precio actual del producto antes de aplicar el régimen fiscal.
     * @return Precio base imponible del producto después de aplicar la tasa de impuestos reducida.
     */
    public double calculateTaxBasePrice(double price) {
        if (getAverageRating() > 3.5) {
            price = (Math.round((price) / ((5.0 / 100.0) + 1.0)));
        } else {
            price = (Math.round((price) / ((10.0 / 100.0) + 1.0)));
        }
        return price;
    }
}
