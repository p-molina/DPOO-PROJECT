package Bussines.Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un carrito de compras, incluyendo una lista de productos del catálogo y el precio total de compra.
 */
public class ShoppingCart {
    private List<CatalogProduct> catalogProductList;
    private double totalBuyPrice;

    /**
     * Constructor para inicializar el carrito de compras.
     */
    public ShoppingCart() {
        this.catalogProductList = new ArrayList<>(0);
        this.totalBuyPrice = 0;
    }
    /**
     * Obtiene la lista de productos en el carrito de compras.
     *
     * @return Lista de productos del catálogo en el carrito.
     */
    public List<CatalogProduct> getCatalogProductList() {
        return catalogProductList;
    }
    /**
     * Establece la lista de productos en el carrito de compras.
     *
     * @param catalogProductList Lista de productos del catálogo a establecer.
     */
    public void setCatalogProductList(List<CatalogProduct> catalogProductList) {
        this.catalogProductList = catalogProductList;
    }
    /**
     * Establece el precio total de los productos en el carrito de compras.
     *
     * @param totalBuyPrice Precio total de compra a establecer.
     */
    public void setTotalBuyPrice(double totalBuyPrice) {
        this.totalBuyPrice = totalBuyPrice;
    }
    /**
     * Limpia el carrito de compras eliminando todos los productos añadidos anteriormente.
     * Esta operación reinicia la lista de productos del catálogo a un estado vacío,
     * pero no modifica el precio total de compra almacenado.
     */
    public void clearCard() {
        this.catalogProductList.clear();
    }
}
