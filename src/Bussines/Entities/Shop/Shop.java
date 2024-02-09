package Bussines.Entities.Shop;

import Bussines.Entities.CatalogProduct;
import Bussines.Entities.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una tienda, incluyendo detalles como nombre, descripción, año de apertura, ingresos, modelo de negocio y lista de productos en el catálogo.
 */
public class Shop {
    private String name;
    private String description;
    private int since;
    private double earnings;
    private String businessModel;
    private List<CatalogProduct> catalogProductList;

    /**
     * Constructor para crear una tienda.
     *
     * @param name           Nombre de la tienda.
     * @param description    Descripción de la tienda.
     * @param since          Año de apertura de la tienda.
     * @param earnings       Ingresos de la tienda.
     * @param businessModel  Modelo de negocio de la tienda.
     */
    public Shop(String name, String description, int since, double earnings, String businessModel) {
        this.name = name;
        this.description = description;
        this.since = since;
        this.earnings = earnings;
        this.businessModel = businessModel;
        this.catalogProductList = new ArrayList<>(0);
    }
    /**
     * Obtiene el nombre de la tienda.
     *
     * @return Nombre de la tienda.
     */
    public String getName() {
        return name;
    }
    /**
     * Obtiene la descripción de la tienda.
     *
     * @return Descripción de la tienda.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Obtiene el año de apertura de la tienda.
     *
     * @return Año de apertura de la tienda.
     */
    public int getSince() {
        return since;
    }
    public String getBusinessModel() {
        return this.businessModel;
    }
    /**
     * Obtiene la lista de productos del catálogo de la tienda.
     *
     * @return Lista de productos del catálogo.
     */
    public List<CatalogProduct> getCatalogProductList() {
        return catalogProductList;
    }
    /**
     * Establece los ingresos de la tienda.
     *
     * @param earnings Ingresos de la tienda.
     */
    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    public double getEarnings() {
        return earnings;
    }

    public static List<Shop> toShopList(String input) {
        Gson gson = new Gson();
        Type shopListType = new TypeToken<ArrayList<Shop>>(){}.getType();
        return gson.fromJson(input, shopListType);
    }
}
