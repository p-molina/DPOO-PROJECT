package Bussines;

import Bussines.Entities.CatalogProduct;
import Bussines.Entities.ShoppingCart;

import java.util.List;

/**
 * Gestiona las operaciones relacionadas con el carrito de compras, permitiendo agregar productos,
 * obtener información detallada del carrito, limpiar el carrito y proceder al checkout.
 */
public class ShoppingCartManager {
    private ShoppingCart shoppingCart;

    /**
     * Constructor que inicializa el gestor del carrito de compras con un nuevo carrito vacío.
     */
    public ShoppingCartManager() {
        this.shoppingCart = new ShoppingCart();
    }

    /**
     * Agrega un producto al carrito de compras.
     *
     * @param catalogProduct Producto del catálogo a ser agregado al carrito.
     */
    public void addProductToCard(CatalogProduct catalogProduct) {
        List<CatalogProduct> cardList = shoppingCart.getCatalogProductList();
        cardList.add(catalogProduct);
        shoppingCart.setCatalogProductList(cardList);
    }

    /**
     * Obtiene la información del carrito de compras, incluyendo la lista de productos y el precio total.
     * Formatea y retorna una cadena de texto que detalla los productos en el carrito y el costo total.
     *
     * @return Una cadena de texto que contiene la información detallada del carrito de compras,
     *         incluyendo cada producto y el precio total de la compra.
     */
    public String getCardInfo() {
        StringBuilder cardInfo = new StringBuilder("\nYour card contains the following items:");
        double totalBuyPrice = 0;
        List<CatalogProduct> cardList = shoppingCart.getCatalogProductList();

        for (CatalogProduct catalogProduct : cardList) {
            cardInfo.append("\n\t-1x ").append(catalogProduct.getNameProduct()).append(" by ").append(catalogProduct.getNameBrand()).append("\n\t\tPrice: ").append(catalogProduct.getPrice());
            totalBuyPrice += catalogProduct.getPrice();
        }
        cardInfo.append("\nTotal: ").append(totalBuyPrice);
        shoppingCart.setTotalBuyPrice(totalBuyPrice);
        return cardInfo.toString();
    }

    /**
     * Limpia todos los productos del carrito de compras, restableciéndolo a su estado inicial vacío.
     */
    public void clearCard() {
        shoppingCart.clearCard();
    }

    /**
     * Prepara el carrito de compras para el checkout, retornando la lista de productos
     * actualmente agregados al carrito.
     *
     * @return Una lista de {@link CatalogProduct} que representa los productos en el carrito de compras.
     */
    public List<CatalogProduct> getCheckoutCard() {
        return shoppingCart.getCatalogProductList();
    }
}
