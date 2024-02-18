package Bussines.Entities;

/**
 * Representa un producto en el catálogo, incluyendo detalles como nombre, marca, tienda y precio.
 */
public class CatalogProduct {
    private String nameProduct;
    private String nameBrand;
    private String nameShop;
    private double price;

    /**
     * Constructor para crear un producto de catálogo.
     *
     * @param nameProduct Nombre del producto.
     * @param nameBrand   Marca del producto.
     * @param nameShop    Nombre de la tienda donde se encuentra el producto.
     * @param price       Precio del producto.
     */
    public CatalogProduct(String nameProduct, String nameBrand,String nameShop, double price) {
        this.nameProduct = nameProduct;
        this.nameBrand = nameBrand;
        this.nameShop = nameShop;
        this.price = price;
    }
    /**
     * Obtiene el nombre del producto.
     *
     * @return Nombre del producto.
     */
    public String getNameProduct() {
        return nameProduct;
    }
    /**
     * Obtiene la marca del producto.
     *
     * @return Marca del producto.
     */
    public String getNameBrand() {
        return nameBrand;
    }
    /**
     * Obtiene el precio del producto.
     *
     * @return Precio del producto.
     */
    public double getPrice() {
        return price;
    }
    /**
     * Obtiene el nombre de la tienda donde se encuentra disponible el producto.
     *
     * @return El nombre de la tienda asociada al producto.
     */
    public String getNameShop() {
        return nameShop;
    }
    /**
     * Establece el precio del producto.
     *
     * @param price El nuevo precio del producto.
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
