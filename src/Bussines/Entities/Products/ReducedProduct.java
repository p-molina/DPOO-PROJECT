package Bussines.Entities.Products;

import Bussines.Entities.Product;

public class ReducedProduct extends Product {
    /**
     * Constructor para crear un producto.
     *
     * @param name     Nombre del producto.
     * @param brand    Marca del producto.
     * @param mrp      Precio máximo de venta al público.
     * @param category Categoría del producto.
     */
    public ReducedProduct(String name, String brand, double mrp, String category) {
        super(name, brand, mrp, category);
    }

    public double calculateTaxBasePrice(double price) {
        if (getAverageRating() > 3.5) {
            price = (Math.round((price) / ((5.0 / 100.0) + 1.0)));
        } else {
            price = (Math.round((price) / ((10.0 / 100.0) + 1.0)));
        }
        return price;
    }
}
