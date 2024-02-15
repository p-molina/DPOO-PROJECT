package Bussines.Entities.Products;

import Bussines.Entities.Review;

import java.util.List;

public class SuperReducedProduct extends Product {

    /**
     * Constructor para crear un producto.
     *
     * @param name     Nombre del producto.
     * @param brand    Marca del producto.
     * @param mrp      Precio máximo de venta al público.
     * @param category Categoría del producto.
     */

    public SuperReducedProduct(String name, String brand, double mrp, String category, List<Review> reviews) {
        super(name, brand, mrp, category, reviews);
    }

    public double calculateTaxBasePrice(double price) {
        if (getMrp() > 100.0) {
            price = (Math.round(price));
        } else {
            price = (Math.round(((price) / ((4.0 / 100.0) + 1.0)) * 100) / 100.0);
        }
        return price;
    }
}
