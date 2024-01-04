package Bussines;

import Bussines.Entities.CatalogProduct;
import Bussines.Entities.ShoppingCart;

import java.util.List;

/**
 * Gestiona las operaciones del carrito de compras.
 */
public class ShoppingCartManager {
    private ShoppingCart shoppingCart;

    /**
     * Constructor para inicializar el gestor del carrito de compras.
     */
    public ShoppingCartManager() {
        this.shoppingCart = new ShoppingCart();
    }
    /**
     * Agrega un producto al carrito de compras.
     *
     * @param catalogProduct Producto del catálogo a agregar al carrito.
     */
    public void addProductToCard(CatalogProduct catalogProduct)
    {
        List<CatalogProduct> cardList = shoppingCart.getCatalogProductList();
        cardList.add(catalogProduct);
        shoppingCart.setCatalogProductList(cardList);
    }
    /**
     * Obtiene la información del carrito de compras, incluyendo los productos y el precio total.
     *
     * @return Cadena de texto con la información del carrito de compras.
     */
    public String getCardInfo()
    {
        String cardInfo = "\nYour card contains the following items:";
        double totalBuyPrice = 0;
        List<CatalogProduct> cardList = shoppingCart.getCatalogProductList();

        for (CatalogProduct catalogProduct : cardList) {
            cardInfo = cardInfo + "\n\t-1x " + catalogProduct.getNameProduct() + " by " + catalogProduct.getNameBrand() + "\n\t\tPrice: " + catalogProduct.getPrice();
        }
        for (CatalogProduct catalogProduct : cardList) {
            totalBuyPrice = totalBuyPrice + catalogProduct.getPrice();
        }
        cardInfo = cardInfo + "\nTotal: " + totalBuyPrice;
        shoppingCart.setTotalBuyPrice(totalBuyPrice);
        return cardInfo;
    }
    public void clearCard()
    {
        shoppingCart.clearCard();
    }
    public List<CatalogProduct> getCheckoutCard()
    {
        return shoppingCart.getCatalogProductList();
    }
}
