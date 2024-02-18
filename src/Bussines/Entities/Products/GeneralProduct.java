package Bussines.Entities.Products;

import Bussines.Entities.Review;
import java.util.List;

/**
 * Representa un producto general sujeto a un régimen fiscal estándar. Este tipo de producto
 * aplica una tasa de impuestos estándar sobre el precio de venta al público.
 */
public class GeneralProduct extends Product {

    /**
     * Constructor para crear un producto general.
     *
     * @param name     Nombre del producto.
     * @param brand    Marca del producto.
     * @param mrp      Precio máximo de venta al público (MRP).
     * @param category Categoría del producto.
     * @param reviews  Lista de reseñas asociadas al producto.
     */
    public GeneralProduct(String name, String brand, double mrp, String category, List<Review> reviews) {
        super(name, brand, mrp, category, reviews);
    }

    /**
     * Calcula el precio base imponible del producto aplicando la tasa de impuestos estándar.
     * En el caso de un producto general, se aplica una tasa fija del 21% sobre el precio
     * para calcular el precio base imponible.
     *
     * @param price Precio actual del producto antes de aplicar el régimen fiscal.
     * @return Precio base imponible del producto después de aplicar la tasa de impuestos estándar del 21%.
     */
    public double calculateTaxBasePrice(double price) {
        price = (Math.round(price / ((21.0 / 100.0) + 1.0)));
        return price;
    }
}
