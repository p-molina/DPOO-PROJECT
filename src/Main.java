import Presentation.CartController;
import Presentation.Menu;
import Presentation.ProductController;
import Presentation.ShopController;

public class Main {
    public static void main(String[] args) {
        //Aqui inicializamos las clases de la capa Persistencia

        //Aqui inicializamos las clases de la capa Bussines

        //Aqui inicializamos las clases de la capa Presentacion
        ProductController productController = new ProductController();
        ShopController shopController = new ShopController();
        CartController cartController = new CartController();

        //Inicializamos el menu y ejecutamos la pantalla principal
        Menu menu = new Menu(productController, shopController, cartController);
        menu.run();
    }
}