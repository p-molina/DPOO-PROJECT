package Presentation;

public class Menu {
    private ProductController productController;
    private ShopController shopController;
    private CartController cartController;

    public Menu(ProductController productController, ShopController shopController, CartController cartController) {
        this.productController = productController;
        this.shopController = shopController;
        this. cartController = cartController;
    }

    public void run() {

    }
}
