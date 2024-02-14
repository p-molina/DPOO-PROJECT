package Bussines.Entities.Products;

import Bussines.Entities.Product;

public class GeneralProduct extends Product {
    /**
     * Constructor para crear un producto.
     *
     * @param name     Nombre del producto.
     * @param brand    Marca del producto.
     * @param mrp      Precio máximo de venta al público.
     * @param category Categoría del producto.
     */
    public GeneralProduct(String name, String brand, double mrp, String category) {
        super(name, brand, mrp, category);
    }
    public double calculateTaxBasePrice(double price) {
        price = (Math.round(price / ((21.0 / 100.0) + 1.0)));
        return price;
    }
}
