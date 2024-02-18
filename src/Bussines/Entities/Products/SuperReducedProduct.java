package Bussines.Entities.Products;

import Bussines.Entities.Review;

import java.util.List;

/**
 * Representa un producto con un régimen fiscal super reducido. Este tipo de producto se beneficia
 * de tasas de impuestos significativamente menores, lo cual se refleja en su método de cálculo del
 * precio base imponible.
 */
public class SuperReducedProduct extends Product {

    /**
     * Constructor para crear un producto super reducido.
     *
     * @param name     Nombre del producto.
     * @param brand    Marca del producto.
     * @param mrp      Precio máximo de venta al público (MRP).
     * @param category Categoría del producto.
     * @param reviews  Lista de reseñas asociadas al producto.
     */
    public SuperReducedProduct(String name, String brand, double mrp, String category, List<Review> reviews) {
        super(name, brand, mrp, category, reviews);
    }
    /**
     * Calcula el precio base imponible del producto aplicando la tasa de impuestos super reducida.
     * Si el MRP del producto es mayor a 100, se asume que el producto no se beneficia de la tasa
     * super reducida y se devuelve el precio sin cambios. De lo contrario, se aplica una tasa de impuestos
     * del 4% para calcular el precio base imponible.
     *
     * @param price Precio actual del producto antes de aplicar el régimen fiscal.
     * @return Precio base imponible del producto después de aplicar la tasa de impuestos super reducida.
     */
    public double calculateTaxBasePrice(double price) {
        if (getMrp() > 100.0) {
            price = (Math.round(price));
        } else {
            price = (Math.round(((price) / ((4.0 / 100.0) + 1.0)) * 100) / 100.0);
        }
        return price;
    }
}
